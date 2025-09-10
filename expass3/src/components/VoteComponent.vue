<template>
  <div class="vote-component">
    <h2>Vote on Polls</h2>

    <input
        type="text"
        v-model="voterUsername"
        placeholder="Your Username"
        required
    />

    <div v-for="poll in polls" :key="poll.id" class="poll">
      <h3>{{ poll.question }}</h3>
      <p>Created by: {{ poll.creatorUsername }}</p>

      <form @submit.prevent="vote(poll.id)">
        <div v-for="option in poll.voteOptions" :key="option.id">
          <label>
            <input
                type="radio"
                :name="`poll-${poll.id}`"
                :value="option.id"
                v-model="selectedOptions[poll.id]"
            />
            {{ option.caption }}
          </label>
        </div>
        <button type="submit" :disabled="!voterUsername || !selectedOptions[poll.id]">
          Vote
        </button>
      </form>
    </div>

    <div v-if="message">{{ message }}</div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const polls = ref([])
const voterUsername = ref('')
const selectedOptions = ref({})
const message = ref('')

const fetchPolls = async () => {
  try {
    const response = await fetch('http://localhost:8080/polls')
    polls.value = await response.json()
  } catch (error) {
    message.value = 'Error fetching polls: ' + error.message
  }
}

const vote = async (pollId) => {
  try {
    const response = await fetch(`http://localhost:8080/polls/${pollId}/votes`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        username: voterUsername.value,
        voteOptionId: selectedOptions.value[pollId]
      })
    })

    if (response.ok) {
      message.value = 'Vote submitted successfully!'
    } else {
      message.value = 'Failed to submit vote'
    }
  } catch (error) {
    message.value = 'Error: ' + error.message
  }
}

onMounted(fetchPolls)
</script>

<style scoped>
.vote-component {
  padding: 20px;
  max-width: 500px;
}

.poll {
  border: 1px solid #ccc;
  padding: 15px;
  margin: 10px 0;
}

input[type="text"], button {
  padding: 10px;
  margin: 5px 0;
}

label {
  display: block;
  margin: 5px 0;
}
</style>