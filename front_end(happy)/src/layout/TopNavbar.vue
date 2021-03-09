<template>
  <nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
      <div class="collapse navbar-collapse justify-content-end">
        <ul class="navbar-nav ml-auto">
          <li v-if="getAccessToken" class="nav-item">
            <a class="nav-link">{{ getUserId }}님 환영합니다.</a>
          </li>
          <li v-if="getAccessToken" class="nav-item">
            <a class="nav-link" @click="logoutgo">Log out</a>
          </li>
          <li v-if="!getAccessToken" class="nav-item">
            <a class="nav-link" @click="login">Log in</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>
<script>
import { mapGetters, mapActions } from "vuex";
export default {
  computed: {
    routeName() {
      const { name } = this.$route;
      return this.capitalizeFirstLetter(name);
    },
    ...mapGetters(["getAccessToken", "getUserId"])
  },
  data() {
    return {
      activeNotifications: false
    };
  },
  methods: {
    ...mapActions(["logout"]),
    capitalizeFirstLetter(string) {
      return string.charAt(0).toUpperCase() + string.slice(1);
    },
    toggleNotificationDropDown() {
      this.activeNotifications = !this.activeNotifications;
    },
    closeDropDown() {
      this.activeNotifications = false;
    },
    toggleSidebar() {
      this.$sidebar.displaySidebar(!this.$sidebar.showSidebar);
    },
    hideSidebar() {
      this.$sidebar.displaySidebar(false);
    },
    login() {
      this.$router.push("/login").catch(function() {
        console.log(""); // then error :  Error: Error in then()
      });
    },
    logoutgo() {
      this.logout();
      this.$router.push("/").catch(function() {
        console.log(""); // then error :  Error: Error in then()
      });
    }
  }
};
</script>
<style></style>
