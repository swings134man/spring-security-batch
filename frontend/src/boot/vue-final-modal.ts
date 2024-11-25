import {boot} from 'quasar/wrappers';
import {createVfm} from 'vue-final-modal';

export default boot(async ({app, router, store}) => {
  const vfm = createVfm()
  app.use(vfm);

  console.log('quasar boot:components initialized');
});
