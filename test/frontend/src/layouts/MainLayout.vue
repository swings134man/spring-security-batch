<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          icon="menu"
          aria-label="Menu"
          @click="toggleLeftDrawer"
        />

        <q-toolbar-title>
          CMS Back Office
        </q-toolbar-title>

        <div style="margin-right: 30px">
          <q-btn
            flat
            dense
            round
            icon="login"
            aria-label="Login"
            label="Login"
            @click="callLoginPage"
          />
        </div>

        <div>
          <q-toggle
            v-model="darkMode"
            color="red"
            label="Dark"
            left-label
            dense
            @click="toggleDarkMode()"
          >

          </q-toggle>
          <div>
            Quasar v{{ $q.version }}
          </div>
        </div>
      </q-toolbar>
    </q-header>

    <q-drawer
      v-model="leftDrawerOpen"
      show-if-above
      bordered
    >
      <q-list>
        <q-item-label
          header
        >
          Essential Links
        </q-item-label>

        <EssentialLink
          v-for="link in linksList"
          :key="link.title"
          v-bind="link"
          :page-link="link.pageLink"
        />
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script setup lang="ts">
import {defineComponent, onMounted, ref, defineProps} from 'vue';
import EssentialLink from 'components/EssentialLink.vue';
import {useQuasar} from "quasar";
import {useThemeStore} from "stores/theme";
import { getCodeFromUrl, exchangeCodeForToken } from 'src/utils/auth';

const linksList = [
  // {
  //   title: 'Docs',
  //   caption: 'quasar.dev',
  //   icon: 'school',
  //   link: 'https://quasar.dev'
  // },
  // {
  //   title: 'Github',
  //   caption: 'github.com/quasarframework',
  //   icon: 'code',
  //   link: 'https://github.com/quasarframework'
  // },
  // {
  //   title: 'Discord Chat Channel',
  //   caption: 'chat.quasar.dev',
  //   icon: 'chat',
  //   link: 'https://chat.quasar.dev'
  // },
  // {
  //   title: 'Forum',
  //   caption: 'forum.quasar.dev',
  //   icon: 'record_voice_over',
  //   link: 'https://forum.quasar.dev'
  // },
  // {
  //   title: 'Twitter',
  //   caption: '@quasarframework',
  //   icon: 'rss_feed',
  //   link: 'https://twitter.quasar.dev'
  // },
  // {
  //   title: 'Facebook',
  //   caption: '@QuasarFramework',
  //   icon: 'public',
  //   link: 'https://facebook.quasar.dev'
  // },
  // {
  //   title: 'Quasar Awesome',
  //   caption: 'Community Quasar projects',
  //   icon: 'favorite',
  //   link: 'https://awesome.quasar.dev'
  // },
  {
    title: 'cms',
    caption: 'cms',
    icon: 'public',
    pageLink: '/',
  },
  {
    title: 'MAIN',
    caption: 'CMS MAIN',
    icon: 'favorite',
    pageLink: '/main',
  }
];

const $q = useQuasar();
const darkMode = ref(false);

const leftDrawerOpen = ref(false);
const store = useThemeStore();

const toggleLeftDrawer = () => {
  leftDrawerOpen.value = !leftDrawerOpen.value;
};

const comp = defineComponent({
  name: 'MainLayout',
  components: {
    EssentialLink
  },
});

// const props = defineProps({
//   essentialLinks: {
//     type: Array,
//     default: () => linksList,
//   },
// });

onMounted(() => {
  console.log('MainLayout mounted');
  init();
  checkAuth();
});

const init = () => {
  setup();
  console.log('setup Complete');
  console.log('is Dark ? ', $q.dark.isActive);
  console.log('Pinia Store Dark', store.dark);
};

const setup = () => {
  return {
    leftDrawerOpen,
  }
};

const checkAuth = () => {
  // TODO: Token has exist in Cookie Or LocalStorage Do Not Call Utils()
  const code = getCodeFromUrl();
  console.log('code :', code);
  if (code) {
    exchangeCodeForToken(code);
  }
}

const toggleDarkMode = () => {
  console.log('toggleDarkMode', darkMode.value);
  $q.dark.set(darkMode.value);
  store.setDark(darkMode.value);

  console.log('Pinia Store Dark', store.dark);
};

const callLoginPage = () => {
  console.log('callLoginPage');
  const authUrl = 'http://localhost:9999/oauth2/authorize';
  const params = new URLSearchParams({
    response_type: 'code',
    client_id: 'multi',
    scope: 'openid',
    redirect_uri: 'http://localhost:9000'
  });

  window.location.href = `${authUrl}?${params.toString()}`;
};
</script>
