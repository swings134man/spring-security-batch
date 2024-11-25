<template>
    <div>
      <h2>Test One</h2>
      {{ data }}

      <!--  Ts Model    -->
      <div class="q-pa-md" v-if="tsData.length > 0">
        <h3>TS Model</h3>
        <q-table
          title="TS Model Table"
          :rows="tsData"
          :columns="testColumns"
          row-key="id"
        />
      </div>
    </div>
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
  import axios from "axios";
import {api} from "boot/axios";
import {Test} from "src/models/test/test.model";

const data = ref({});
const tsData = ref<Test>({});

const testColumns = [
  { name: "id", label: "ID", align: "left", field: "id" },
  { name: "message", label: "메세지", align: "center", field: "message" },
  { name: "createdDate", label: "생성일", align: "right", field: "createdDate" },
  { name: "updateDate", label: "수정일", align: "right", field: "updateDate" }
];

  onMounted(() => {
    init();
  });

  function init() {
    console.log('test-one init');
    retrieve();
  }

  function retrieve() {
    api.get('/test/get')
      .then((res) => {
        console.log(res.data);
        data.value = res.data;
        // TS Model
        tsData.value = res.data as Test;
        console.log(tsData.value);
      })
      .catch((err) => {
        console.log(err);
      })
  }
</script>

<style scoped>

</style>
