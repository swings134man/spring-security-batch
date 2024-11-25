import {defineStore} from 'pinia';

interface ThemeState {
  dark: boolean
}

export const useThemeStore = defineStore('theme', {
  state: () => ({
    dark: false,
  } as ThemeState),

  getters: {},

  actions: {
    setDark(dark: boolean) {
      this.dark = dark;
    }
  }

});
