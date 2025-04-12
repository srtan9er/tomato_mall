package com.example.tomatomall.servicelmpl;

import com.example.tomatomall.exception.TomatoException;
import com.example.tomatomall.po.Product;
import com.example.tomatomall.po.Stockpile;
import com.example.tomatomall.repository.ProductRepository;
import com.example.tomatomall.repository.StockpileRepository;
import com.example.tomatomall.service.ProductService;
import com.example.tomatomall.vo.ProductVO;
import com.example.tomatomall.vo.StockpileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.net.URL;
import java.net.URI;
import com.example.tomatomall.po.Store;
import com.example.tomatomall.po.User;
import com.example.tomatomall.repository.StoreRepository;
import com.example.tomatomall.util.SecurityUtil;

@Service
public class ProductServicelmpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private StockpileRepository stockpileRepository;
    
    @Autowired
    private SecurityUtil securityUtil;  // 添加用户工具类
    
    @Autowired
    private StoreRepository storeRepository;  // 添加店铺仓库

    @Override
    public List<ProductVO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(Product::toVO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductVO getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new TomatoException("商品不存在"));
        return product.toVO();
    }

    @Override
    @Transactional
    public ProductVO createProduct(ProductVO productVO) {
        // 获取当前用户
        User currentUser = securityUtil.getCurrentUser();
        if (currentUser == null || !currentUser.getRole().equals("ADMIN")) {
            throw new TomatoException("无权限创建商品");
        }

        // 验证商品所属店铺
        Store store = storeRepository.findById(productVO.getStoreId())
                .orElseThrow(() -> new TomatoException("店铺不存在"));
        
        // 验证是否为店铺所有者
        if (!store.getOwnerId().equals(currentUser.getId())) {
            throw new TomatoException("只能在自己的店铺中创建商品");
        }

        // 验证必填字段
        if (productVO.getTitle() == null || productVO.getPrice() == null || productVO.getRate() == null) {
            throw new TomatoException("商品名称、价格和评分为必填项");
        }
        
        // 验证价格和评分范围
        if (productVO.getPrice().doubleValue() < 0) {
            throw new TomatoException("商品价格不能小于0");
        }
        if (productVO.getRate() < 0 || productVO.getRate() > 10) {
            throw new TomatoException("商品评分必须在0-10之间");
        }

        Product product = productVO.toPO();
        product.setStore(store);  // 设置商品所属店铺
        product = productRepository.save(product);
        
        // 初始化库存
        Stockpile stockpile = new Stockpile();
        stockpile.setProduct(product);
        stockpile.setAmount(0);
        stockpile.setFrozen(0);
        stockpileRepository.save(stockpile);
        
        return product.toVO();
    }

    @Override
    @Transactional
    public String deleteProduct(Integer id) {
        // 获取当前用户
        User currentUser = securityUtil.getCurrentUser();
        if (currentUser == null || !currentUser.getRole().equals("ADMIN")) {
            throw new TomatoException("无权限删除商品");
        }
    
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new TomatoException("商品不存在"));
    
        // 验证是否为店铺所有者
        if (!product.getStore().getOwnerId().equals(currentUser.getId())) {
            throw new TomatoException("只能删除自己店铺的商品");
        }
        
        // 删除关联的库存记录
        Stockpile stockpile = stockpileRepository.findByProductId(id)
                .orElseThrow(() -> new TomatoException("商品库存记录不存在"));
        stockpileRepository.delete(stockpile);
        
        // 删除商品
        productRepository.delete(product);
        return "删除成功";
    }

    @Override
    @Transactional
    public String adjustStockpile(Integer productId, Integer amount) {
        // 获取当前用户
        User currentUser = securityUtil.getCurrentUser();
        if (currentUser == null || !currentUser.getRole().equals("ADMIN")) {
            throw new TomatoException("无权限调整库存");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new TomatoException("商品不存在"));

        // 验证是否为店铺所有者
        if (!product.getStore().getOwnerId().equals(currentUser.getId())) {
            throw new TomatoException("只能调整自己店铺商品的库存");
        }

        if (amount < 0) {
            throw new TomatoException("库存数量不能为负数");
        }

        Stockpile stockpile = stockpileRepository.findByProductId(productId)
                .orElseThrow(() -> new TomatoException("商品不存在"));
        
        if (amount < stockpile.getFrozen()) {
            throw new TomatoException("库存数量不能小于已冻结数量");
        }
        
        stockpile.setAmount(amount);
        stockpileRepository.save(stockpile);
        return "调整库存成功";
    }

    @Override
    public StockpileVO getStockpile(Integer productId) {
        Stockpile stockpile = stockpileRepository.findByProductId(productId)
                .orElseThrow(() -> new TomatoException("商品不存在"));
        
        StockpileVO vo = new StockpileVO();
        vo.setId(stockpile.getId().toString());
        vo.setAmount(stockpile.getAmount());
        vo.setFrozen(stockpile.getFrozen());
        vo.setProductId(stockpile.getProduct().getId().toString());
        return vo;
    }

    @Override
    @Transactional
    public void updateProduct(ProductVO productVO) {
        // 获取当前用户
        User currentUser = securityUtil.getCurrentUser();
        if (currentUser == null || !currentUser.getRole().equals("ADMIN")) {
            throw new TomatoException("无权限修改商品");
        }

        Product product = productRepository.findById(productVO.getId())
                .orElseThrow(() -> new TomatoException("商品不存在"));

        // 验证是否为店铺所有者
        if (!product.getStore().getOwnerId().equals(currentUser.getId())) {
            throw new TomatoException("只能修改自己店铺的商品");
        }

        // 添加验证逻辑
        if (productVO.getPrice() != null && productVO.getPrice().doubleValue() < 0) {
            throw new TomatoException("商品价格不能小于0");
        }
        if (productVO.getRate() != null && (productVO.getRate() < 0 || productVO.getRate() > 10)) {
            throw new TomatoException("商品评分必须在0-10之间");
        }
        
        // 更新商品信息
        // 在 createProduct 和 updateProduct 方法中添加规格验证
        //if (productVO.getSpecifications() != null) {
         //   for (Specification spec : productVO.getSpecifications()) {
        //        if (spec.getName() == null || spec.getValue() == null) {
         //           throw new TomatoException("商品规格的名称和值不能为空");
         //       }
         //   }
        //}
        if (productVO.getCover() != null && !isValidUrl(productVO.getCover())) {
            throw new TomatoException("商品封面图片URL格式不正确");
        }
        if (productVO.getDetail() != null && !isValidUrl(productVO.getDetail())) {
            throw new TomatoException("商品详情图片URL格式不正确");
        }

        if (productVO.getTitle() != null && productVO.getTitle().length() > 50) {
            throw new TomatoException("商品标题长度不能超过50个字符");
        }
        if (productVO.getTitle() != null) product.setTitle(productVO.getTitle());
        if (productVO.getPrice() != null) product.setPrice(productVO.getPrice());
        if (productVO.getRate() != null) product.setRate(productVO.getRate());
        if (productVO.getDescription() != null) product.setDescription(productVO.getDescription());
        if (productVO.getCover() != null) product.setCover(productVO.getCover());
        if (productVO.getDetail() != null) product.setDetail(productVO.getDetail());
        if (productVO.getSpecifications() != null) product.setSpecifications(productVO.getSpecifications());
        
        productRepository.save(product);
    }

    // 将 isValidUrl 方法移到类级别
    private boolean isValidUrl(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

