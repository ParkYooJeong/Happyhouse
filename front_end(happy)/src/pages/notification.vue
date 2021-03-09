<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <card
            class="strpied-tabled-with-hover"
            body-classes="mx-auto table-full-width table-responsive"
          >
            <template slot="header">
              <center>
                <h2 class="card-title">Notification</h2>
              </center>
            </template>

            <br />
            <div class="mx-auto row" style="width: 500px;">
              <div class="col-md-3">
                <b-form-select
                  v-model="boardSearch.option"
                  :options="options"
                ></b-form-select>
              </div>
              <div class="col-md-6">
                <base-input
                  type="text"
                  v-model="boardSearch.content"
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

            <b-table
              class="table-hover table-striped"
              :fields="tableColumns"
              :items="getBoardList"
              @row-clicked="item => pop(item)"
            >
            </b-table>
            <button
              type="submit"
              class="btn btn-info btn-fill float-right"
              @click.prevent="$bvModal.show('insert')"
            >
              글작성
            </button>

            <!-- 모달창(수정삭제) -->
            <b-modal id="info" hide-footer>
              <card>
                <br />
                <center>
                  <h4 slot="header" class="card-title">공지사항</h4>
                </center>
                <br />
                <form>
                  <div class="row">
                    <div class="col-md-12">
                      <base-input
                        type="text"
                        label="제목"
                        v-model="board.title"
                      >
                      </base-input>
                    </div>
                  </div>
                  <div class="row"></div>
                  <div class="row">
                    <div class="col-md-3">
                      <base-input
                        type="text"
                        label="작성자"
                        v-model="board.name"
                        readonly
                      >
                      </base-input>
                    </div>
                    <div class="col-md-6">
                      <base-input
                        type="text"
                        label="날짜"
                        v-model="board.wdate"
                        readonly
                      >
                      </base-input>
                    </div>
                    <div class="col-md-3">
                      <base-input
                        type="text"
                        label="조회수"
                        v-model="board.count"
                        readonly
                      >
                      </base-input>
                    </div>
                  </div>

                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label>Content</label>
                        <textarea
                          rows="5"
                          class="form-control border-input"
                          placeholder="Here can be your description"
                          v-model="board.content"
                        >
                        </textarea>
                      </div>
                    </div>
                  </div>
                  <div class="text-center">
                    <button
                      type="submit"
                      class="btn btn-info btn-fill float-right"
                      @click.prevent="updateBoard"
                    >
                      Update Board
                    </button>
                    <button
                      type="submit"
                      class="btn btn-info btn-fill float-left"
                      @click.prevent="deleteBoard(board.num)"
                    >
                      Delete
                    </button>
                  </div>

                  <div class="clearfix"></div>
                </form>
              </card>
              <b-button class="mt-2" block @click="close">Close Me</b-button>
            </b-modal>

            <!-- 공지사항 입력 모달창 -->
            <b-modal id="insert" hide-footer>
              <card>
                <br />
                <center>
                  <h4 slot="header" class="card-title">공지사항</h4>
                </center>
                <br />
                <form>
                  <div class="row">
                    <div class="col-md-8">
                      <base-input
                        type="text"
                        label="제목"
                        v-model="boardInput.title"
                      >
                      </base-input>
                    </div>
                    <div class="col-md-4">
                      <base-input
                        type="text"
                        label="작성자"
                        v-model="userId"
                        readonly
                      >
                      </base-input>
                    </div>
                  </div>

                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label>Content</label>
                        <textarea
                          rows="5"
                          class="form-control border-input"
                          placeholder="Here can be your description"
                          v-model="boardInput.content"
                        >
                        </textarea>
                      </div>
                    </div>
                  </div>
                  <div class="text-center">
                    <button
                      type="submit"
                      class="btn btn-info btn-fill float-right"
                      @click.prevent="insertBoard"
                    >
                      글작성
                    </button>
                  </div>

                  <div class="clearfix"></div>
                </form>
              </card>
              <b-button class="mt-2" block @click="close2">Close Me</b-button>
            </b-modal>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapActions, mapState } from "vuex";

export default {
  data() {
    return {
      word: "",
      board: "",
      boardInput: {
        name: "",
        title: "",
        content: ""
      },
      options: [
        { value: "content", text: "contnet" },
        { value: "title", text: "title" }
      ],
      boardSearch: {
        option: "content",
        content: ""
      },
      tableColumns: ["num", "title", "name", "wdate"]
    };
  },
  computed: {
    ...mapGetters(["getBoardList", "getUserId"]),
    ...mapState(["userId"])
  },
  created() {
    this.selectAllB();
  },
  methods: {
    ...mapActions([
      "selectAllB",
      "searchB",
      "updateB",
      "deleteB",
      "insertB",
      "searchTitleB"
    ]),
    insertBoard() {
      this.boardInput.name = this.userId;
      this.insertB(this.boardInput);
      this.selectAllB();
      this.$bvModal.hide("insert");
    },
    pop(item) {
      this.board = item;
      this.$bvModal.show("info");
    },
    close() {
      this.selectAllB();
      this.$bvModal.hide("info");
    },
    close2() {
      this.selectAllB();
      this.$bvModal.hide("insert");
    },
    updateBoard() {
      this.updateB(this.board);
      this.selectAllB();
      this.$bvModal.hide("info");
    },
    deleteBoard(num) {
      this.deleteB(num);
      this.selectAllB();
      this.$bvModal.hide("info");
    },
    search() {
      if (this.boardSearch.content == "") {
        this.selectAllB();
      } else if (this.boardSearch.option == "content") {
        this.searchB(this.boardSearch.content);
      } else {
        this.searchTitleB(this.boardSearch.content);
      }
      this.boardSearch.content = "";
    }
  }
};
</script>

<style></style>
