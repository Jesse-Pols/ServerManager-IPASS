<template>
    <div class="login">
        <h5>Gebruik uw emailadres en wachtwoord om in te loggen.</h5>

        <form @submit.prevent="callLogin()">
            <input type="text" placeholder="E-mail" v-model="user.email">
            <input type="text" placeholder="Wachtwoord" v-model="user.pass">
            <b-btn variant="success" type="submit">Inloggen</b-btn>
            <p v-if="error" class="error">De inloggegevens zijn incorrect</p>
        </form>
    </div>   
</template>

<script>
export default {
  name: 'login',

  data () {
    return {
        user: {
            email: 'jessepols@gmail.com',
            pass: ''
        },
        loginError: false,
        error: false,
        errors: []
    }
  },
  methods: {
    callLogin() {
      this.errors = [];
      this.$store.dispatch("login", { email: this.user.email, password: this.user.pass})
        .then(() => {
          this.$router.push('/controlpanel')
        })
        .catch(error => {
          this.loginError = true;
          this.errors.push(error);
          this.error = true
        })
    }
  }
}
</script>

<style>

</style>
