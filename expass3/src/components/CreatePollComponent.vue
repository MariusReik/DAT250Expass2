<template>
  <div class="create-poll">
    <h2>Create Poll</h2>
    <form @submit.prevent="createPoll">
      <input
          type="text"
          v-model="creatorUsername"
          placeholder="Your Username"
          required
      />
      <input
          type="text"
          v-model="question"
          placeholder="Poll Question"
          required
      />
      <input
          type="datetime-local"
          v-model="validUntil"
          required
      />

      <div v-for="(option, index) in voteOptions" :key="index">
        <input
            type="text"
            v-model="option.caption"
            :placeholder="`Option ${index + 1}`"
            required
        />
      </div>

      <button type="button" @click="addOption">Add Option</button>
      <button type="submit">Create Poll</button>
    </form>
    <div v-if="message">{{ message }}</div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const creatorUsername = ref('')
const question = ref('')
const validUntil = ref('')
const voteOptions = ref([
  { caption: '', presentationOrder: 1 },
  { caption: '', presentationOrder: 2 }
])
const message = ref('')

const addOption = () => {
  voteOptions.value.push({
    caption: '',
    presentationOrder: voteOptions.value.length + 1
  })
}

// In CreatePollComponent - modify the createPoll function
const createPoll = async () => {
  try {
    const response = await fetch('http://localhost:8080/polls', {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({
        question: question.value,
        creatorUsername: creatorUsername.value,
        validUntil: new Date(validUntil.value).toISOString(),
        voteOptions: voteOptions.value
      })
    })

    if (response.ok) {
      message.value = 'Poll created successfully!'
      // Reset form
      question.value = ''
      creatorUsername.value = ''
      validUntil.value = ''
      voteOptions.value = [
        {caption: '', presentationOrder: 1},
        {caption: '', presentationOrder: 2}
      ]

      // Refresh the page to show new poll
      window.location.reload()
    } else {
      message.value = 'Failed to create poll'
    }
  } catch (error) {
    message.value = 'Error: ' + error.message
  }
}
</script>

<style scoped>
.create-poll {
  padding: 20px;
  max-width: 400px;
}

form {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

input, button {
  padding: 10px;
}
</style>