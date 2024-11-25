import _ from 'lodash';
import {boot} from 'quasar/wrappers';

export default boot(async ({app, router, store}) => {
  window['_'] = _;
  app.config.globalProperties['_d'] = _;
});
