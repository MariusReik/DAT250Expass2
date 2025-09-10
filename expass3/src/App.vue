<script setup>
import CreatePollComponent from "./components/CreatePollComponent.vue";
import VoteComponent from "./components/VoteComponent.vue";
</script>

<template>
  <div id="app">
    <h1>Poll Application</h1>

    <div class="components">
      <CreatePollComponent />
      <VoteComponent />
    </div>
  </div>
</template>

<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
