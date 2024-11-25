<template>
  <h2>Main Page</h2>

  <q-page class="row items-center justify-evenly">

    <!--  임시   -->
    <q-list v-model="list">
      <q-btn
        v-for="item in list"
        :key="item.label"
        :label="item.label"
        color="primary"
        @click="$router.push(item.link)">
      </q-btn>
      <q-btn
        label="Test APIS"
        color="primary"
        @click="test()"/>
    </q-list>

  </q-page>
</template>


<script setup lang="ts">

import axios from "axios";
import {api} from "boot/axios";

const list = [
  {label: 'test1', link: '/test1'},
]

const test = () => {
  console.log('test');
  api.get('http://localhost:9999/test')
    .then((res) => {
      console.log(res.data);
    })
    .catch((err) => {
      console.log(err);
    })
}

</script>

<style scoped>
</style>
