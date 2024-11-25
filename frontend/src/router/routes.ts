import { RouteRecordRaw } from 'vue-router';

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [{ path: '', component: () => import('pages/IndexPage.vue') },
      {path: 'main',
        name: 'main',
        component: () => import('pages/main/main-page.vue')
      },
      {
        path: '/test',
        component: () => import('layouts/MainLayout.vue'),
        children: [
          {
            path: '/test1',
            name: 'test1',
            component: () => import('pages/test/test-one.vue')
          },
        ],
      },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue'),
  },
];

export default routes;
