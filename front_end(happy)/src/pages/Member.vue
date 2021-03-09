<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row" id="ta">
        <div class="col-12">
          <card
            class="strpied-tabled-with-hover"
            body-classes="mx-auto table-full-width table-responsive"
          >
            <template slot="header">
              <center>
                <br />
                <h2 class="card-title">Member List</h2>
              </center>
              <!-- select  -->
              <br />
              <div class="mx-auto row" style="width: 500px;">
                <div class="col-md-3">
                  <b-form-select
                    v-model="mem.option"
                    :options="options"
                  ></b-form-select>
                </div>
                <div class="col-md-6">
                  <base-input
                    type="text"
                    v-model="mem.content"
                    @keypress.enter="search()"
                  >
                  </base-input>
                </div>
                <div class="col-md-3">
                  <button
                    type="submit"
                    class="btn btn-info btn-fill float-left"
                    style="margin-top:0px"
                    @click="search()"
                  >
                    검색
                  </button>
                </div>
              </div>
              <br />
              <!-- 테이블 -->
            </template>
            <b-table
              class="table-hover table-striped"
              :fields="tableColumns"
              :items="getMemberList"
              @row-clicked="item => pop(item)"
            >
            </b-table>

            <!-- 모달 -->

            <b-modal id="info" hide-footer>
              <card>
                <br />
                <center>
                  <h4 slot="header" class="card-title">Member Info</h4>
                </center>
                <br />
                <form>
                  <div class="row">
                    <div class="col-md-12">
                      <base-input
                        type="text"
                        label="ID"
                        v-model="member.id"
                        readonly
                      >
                      </base-input>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <base-input
                        type="text"
                        label="Name"
                        v-model="member.name"
                      >
                      </base-input>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <base-input
                        type="text"
                        label="PASSWORD"
                        v-model="member.pass"
                      >
                      </base-input>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <base-input type="text" label="Tel" v-model="member.tel">
                      </base-input>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <base-input
                        type="text"
                        label="Address"
                        placeholder="Home Address"
                      >
                      </base-input>
                    </div>
                  </div>
                  <div class="text-center">
                    <button
                      type="submit"
                      class="btn btn-info btn-fill float-right"
                      @click.prevent="updateProfile"
                    >
                      Update Profile
                    </button>
                    <button
                      type="submit"
                      class="btn btn-info btn-fill float-left"
                      @click.prevent="deleteMember(member.id)"
                    >
                      Delete
                    </button>
                  </div>

                  <div class="clearfix"></div>
                </form>
              </card>
              <b-button class="mt-2" block @click="close">Close Me</b-button>
            </b-modal>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Card from "src/components/Cards/Card.vue";
import { mapGetters, mapActions } from "vuex";

export default {
  components: {
    Card
  },
  created() {
    this.selectAll();
  },
  computed: {
    ...mapGetters(["getMemberList"])
  },
  data() {
    return {
      tableColumns: ["id", "name", "pass", "tel"],
      member: "",
      options: [
        { value: "id", text: "id" },
        { value: "name", text: "name" }
      ],
      mem: {
        option: "id",
        content: ""
      }
    };
  },
  methods: {
    ...mapActions(["selectAll", "delete", "searchinfo", "selectOne", "update"]),
    remove(id) {
      this.delete(id);
    },
    search() {
      if (this.mem.content == "") {
        this.selectAll();
      } else {
        this.searchinfo(this.mem);
      }
      this.mem.content = "";
    },
    detail(member) {
      this.selectOne(member);
      this.$router.push("/detail");
    },
    pop(item) {
      this.member = item;
      console.log(item);
      this.$bvModal.show("info");
    },
    close() {
      this.selectAll();
      this.$bvModal.hide("info");
    },
    updateProfile() {
      this.update(this.member);
      this.selectAll();
      this.$bvModal.hide("info");
    },
    deleteMember(id) {
      this.delete(id);
      this.selectAll();
      this.$bvModal.hide("info");
    }
  },
  filters: {
    short(val) {
      return val.substring(0, 10);
    }
  }
};
</script>
<style>
/* .b-table {
  margin-left: 10px !important;
} */
</style>
