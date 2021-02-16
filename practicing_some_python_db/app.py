from utils import database
import re
import os

user_choice = """Enter:
- 'a' to add a new book;
- 'l' to list all books;
- 'r' to mark a book as read;
- 'd' to delete a book;
- 'q' to quit.

Your choice: """


def menu():
    # if not os.path.exists("books.json"):  # in case you wanna use some json file instead of a database
    #     database.create_book_table()
    user_input = input(user_choice).lower()
    while user_input != 'q':
        if user_input == 'a':
            prompt_add_book()
        elif user_input == 'l':
            list_books()
        elif user_input == 'r':
            prompt_read_book()
        elif user_input == 'd':
            prompt_delete_book()
        else:
            print("Unknown command. Please try again.")
        user_input = input(user_choice).lower()


def prompt_add_book():
    book_title = input("Please type the book title: ")
    if not re.match("^\\w+(\\s\\w+)*$", book_title):
        print("Invalid book title, try again.")
        return
    year = input("The year the book was released: ")
    if not re.match("^[0-9]{4}$", year):
        print("Invalid year, try again.")
        return
    authors_name = input("Please type the author's name: ")
    if not re.match("^\\w+(\\s\\w+)*$", authors_name):
        print("Invalid author's name, try again.")
        return
    database.add_book(book_title, authors_name)


def prompt_read_book():
    book_to_be_read = input("Please type the title of the book you just finished reading: ")
    if not re.match("^\\w+(\\s\\w+)*$", book_to_be_read):
        print("Invalid book title, try again.")
        return
    database.mark_book_as_read(book_to_be_read)


def prompt_delete_book():
    book_title = input("Book to be deleted: ")
    if not re.match("^\\w+(\\s\\w+)*$", book_title):
        print("Invalid book title, try again.")
        return
    database.prompt_delete_book(book_title)


def list_books():
    print(database.get_all_books())


if __name__ == '__main__':
    menu()
    # database.create_book_tables()
    # database.add_book("Dom Casmurro", "1899", "Machado de Assis")
    # database.add_book("Quincas Borba", "1891", "Machado de Assis")
    # database.add_book("Iracema", "1865", "Jose de Alencar")
    # database.mark_book_as_read("Dom Casmurro")
    # database.prompt_delete_book("Dom Casmurro")
    # database.add_author("Machado de Assis", 1839, "Brazil")
    # database.add_author("Jose de Alencar", 1829, "Brazil")
    # print(database.query_author_id_by_name("Machado"))
    # print(database.query_author_id_by_name("Alencar"))
    # print(database.query_author_id_by_name("a"))
    # print(database.query_author_id_by_name("Seila"))
    # print(database.get_all_authors())

    # database.create_book_table_sqlite3()
    # database.add_book_sqlite3("Clean Code2", "Robert")

# if __name__ == '__main__':
#     menu()
