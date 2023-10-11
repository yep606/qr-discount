app = Vue.createApp({
    data() {
        return {
            url: "https://www.google.com",
            books: [
                {
                    author: "J.K. Rowling",
                    title: "Harry Potter and the Sorcerer's Stone",
                    img: "assets/1.jpeg",
                    isFav: true,
                },

                {
                    author: "Jane Austen",
                    title: "Pride and Prejudice",
                    img: "assets/2.jpeg",
                    isFav: true,
                },

                {
                    author: "Mark Twain",
                    title: "The Adventures of Huckleberry Finn",
                    img: "assets/3.jpeg",
                    isFav: false,
                },
            ],
            showBooks: true,
            x: 0,
            y: 0,
        };
    },
    methods: {
        toggleShowBooks() {
            this.showBooks = !this.showBooks;
        },
        changeFavs(book){
            book.isFav = !book.isFav;
        }
    },
});

app.mount("#app");