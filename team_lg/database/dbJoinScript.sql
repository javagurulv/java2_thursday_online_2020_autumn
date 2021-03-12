SELECT * FROM readers
INNER JOIN reader_books
ON readers.id=1
WHERE reader_id=1 and book_return_date IS NULL;

SELECT firstName,lastName FROM readers
INNER JOIN reader_books
ON readers.id=reader_books.reader_id
WHERE reader_books.book_id=1;

SELECT bookTitle,bookAuthor FROM books
INNER JOIN reader_books
ON books.id = reader_books.book_id
GROUP BY books.id;