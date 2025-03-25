import gsap from 'gsap';
import ScrollTrigger from 'gsap/ScrollTrigger';

// 注册 ScrollTrigger 插件
gsap.registerPlugin(ScrollTrigger);

// 淡入动画
export const fadeIn = (element, delay = 0) => {
  gsap.to(element, {
    opacity: 1,
    duration: 1,
    delay,
    visibility: 'visible'
  });
};

// 从下方滑入
export const slideFromBottom = (element, delay = 0) => {
  gsap.from(element, {
    y: 100,
    opacity: 0,
    duration: 1,
    delay,
    ease: 'power3.out'
  });
};

// 滚动触发动画
export const scrollTriggerAnimation = (element) => {
  gsap.from(element, {
    scrollTrigger: {
      trigger: element,
      start: 'top bottom-=100',
      toggleActions: 'play none none reverse'
    },
    y: 50,
    opacity: 0,
    duration: 1,
    ease: 'power2.out'
  });
};