package com.example.kotlin_classes.abschluss_abgabe

data class Book(
    val title: String,
    val author:String,
    val genre: Genre,
    var status: BookStatus
)

enum class Genre(private val description: String) {
    FICTION("Fictional stories and novels"),
    NON_FICTION("Non-fiction stories and novels"),
    SCIENCE("Scientific books about scientific science"),
    HISTORY("Historic books about historic events"),
    CHILDREN("Books for children");

    fun returnDescription(): String {
        println(description)
        return description
    }
}

sealed class BookStatus {
    object Available : BookStatus()
    class CheckedOut(val dueDate: String) : BookStatus()
    class Reserved(val reservedBy: String) : BookStatus()

    fun printStatus(): String {
        return when (this) {
            is Available -> "Available"
            is CheckedOut -> "CheckedOut"
            is Reserved -> "Reserved"
        }
    }
}

class Library(val libraryName: String) {

    val books = mutableListOf<Book>()
    fun addBook(book: Book) {
        books.add(book)
    }
    fun searchByTitle(title: String): List<Book> {
        return books.filter { it.title == title }
    }
    fun searchByAuthor(author: String): List<Book> {
        return books.filter { it.author == author }
    }
    fun printStatus() {
        for (book in books) {
            println("Title: ${book.title}, Author: ${book.author}, Genre: ${book.genre}, Status: ${book.status.printStatus()}")
        }
    }
    class LibraryAddress(val street: String, val city: String, val zipCode: String) {
        fun printAddress() = "Library is located at: $street, $city, $zipCode"
    }

    inner class LibraryMember(val name: String, val memberID: String) {
        fun checkoutBook(book: Book, dueDate: String) {
            book.status = BookStatus.CheckedOut(dueDate)
        }
        fun reserveBook(book: Book) {
            book.status = BookStatus.Reserved(name)
        }
    }
}

fun main() {
    val library = Library("Stadtbücherei")
    val address = Library.LibraryAddress("Hauptstrasse 19", "Boennigheim", "74357")

    library.addBook(Book("Harry Potter und der Stein der Weisen", "J.K. Rowling", Genre.FICTION, BookStatus.Available))
    library.addBook(Book("Harry Potter und die Kammer des Schreckens", "J.K. Rowling", Genre.FICTION, BookStatus.Available))
    library.addBook(Book("Herr der Ringe", "J.R.R. Tolkien", Genre.FICTION, BookStatus.Available))

    println(address.printAddress())

    val member = library.LibraryMember("Robin Jung", "12345")
    member.checkoutBook(library.books[0], "12.06.2025")
    member.reserveBook(library.books[1])

    library.printStatus()




}