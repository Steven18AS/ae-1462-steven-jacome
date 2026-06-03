package com.pucetec.ae1462exam1.services

import com.pucetec.ae1462exam1.dto.BookRequest
import com.pucetec.ae1462exam1.dto.BookResponse
import com.pucetec.ae1462exam1.entities.Book
import com.pucetec.ae1462exam1.repositories.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(private val repository: BookRepository) {

    fun createBook(request: BookRequest): BookResponse {

        val normalizedTitle = normalizeText(request.title)
        val normalizedAuthor = normalizeText(request.author)


        val bookEntity = Book(
            title = normalizedTitle,
            author = normalizedAuthor,
            priceUsd = request.priceUsd
        )


        val savedBook = repository.save(bookEntity)


        return mapToResponse(savedBook)
    }

    fun getAllBooks(): List<BookResponse> {

        val books = repository.findAll()


        return books.map { mapToResponse(it) }.sortedBy { it.title }
    }



    private fun normalizeText(text: String): String {

        return text.trim()
            .split("\\s+".toRegex())
            .joinToString(" ") { word ->
                word.lowercase().replaceFirstChar { it.uppercase() }
            }
    }

    private fun mapToResponse(book: Book): BookResponse {

        val slug = book.title.lowercase().replace(" ", "-")


        val finalPrice = book.priceUsd * 1.12

        return BookResponse(
            id = book.id!!,
            title = book.title,
            author = book.author,
            slug = slug,
            priceUsd = book.priceUsd,
            finalPrice = finalPrice
        )
    }
}