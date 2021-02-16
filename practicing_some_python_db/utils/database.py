"""
This file is for me to practice dealing with MySQL (which I'm used to dealing with) and SQLite3.
The only part I've coded inside this file was the MySQL part.
"""

# pip install mysql-connector-python
import re
import sqlite3
import mysql.connector
from mysql.connector import errorcode

"""Concerned with storing and retrieving books from a database"""


# SQLite3 from now on


def add_book_sqlite3(book_title, author):
    with sqlite3.connect("data.db") as db:
        cursor = db.cursor()
        cursor.execute("INSERT INTO book VALUES (?, ?, 0)", (book_title, author))
        # in SQLite3, the question mark works the same way as prepared statements,
        # but it doesn't seem to happen the same way when it comes to MySQL
        db.commit()
        cursor.close()


def create_book_table_sqlite3():
    with sqlite3.connect('data.db') as db:
        cursor = db.cursor()
        cursor.execute(
            "CREATE TABLE IF NOT EXISTS book(name TEXT PRIMARY KEY NOT NULL, author TEXT, is_read INTEGER)")
        db.commit()
        cursor.close()


def get_all_books_sqlite3():
    with sqlite3.connect("data.db") as db:
        cursor = db.cursor()
        cursor.execute("SELECT * FROM book")
        # books = cursor.fetchall()
        return [{'title': row[0], 'author': row[1], 'is_read': row[2]} for row in cursor.fetchall()]


# MySQL from now on


def get_connection():
    try:
        return mysql.connector.connect(user="root",
                                       password="1dG81CvAkA",
                                       host="127.0.0.1",
                                       database="pythonBooksAndAuthors")
    except mysql.connector.Error as error:
        if error.errno == errorcode.ER_ACCESS_DENIED_ERROR:
            print("Username and/or password are wrong.")
        elif error.errno == errorcode.ER_BAD_DB_ERROR:
            print("This database doesn't exist.")
        else:
            print("Something went wrong:")
            print(error)


def create_book_tables():
    with get_connection() as connection:
        with connection.cursor() as cursor:
            cursor.execute("CREATE TABLE IF NOT EXISTS author (" +
                           "  id INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT," +
                           "  name VARCHAR(200)  NULL  ," +
                           "  birth INTEGER UNSIGNED  NULL  ," +
                           "  country VARCHAR(200)  NULL    ," +
                           "PRIMARY KEY(id));")
            cursor.execute("CREATE TABLE IF NOT EXISTS book (" +
                           "  id INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT," +
                           "  author_id INTEGER UNSIGNED  NOT NULL  ," +
                           "  title VARCHAR(200)  NOT NULL  ," +
                           "  yearReleased INTEGER UNSIGNED  NOT NULL    ," +
                           "  isRead TINYINT(1)  NOT NULL  ," +
                           "PRIMARY KEY(id)  ," +
                           "INDEX book_FKIndex1(author_id)," +
                           "  FOREIGN KEY(author_id)" +
                           "    REFERENCES author(id)" +
                           "      ON DELETE NO ACTION" +
                           "      ON UPDATE NO ACTION);")


def add_book(book_title, year_released, authors_name):
    book_id = query_book_id_by_its_full_title(book_title)
    if book_id > 0:
        print("Book already exists.")
        return False
    author_id = query_author_id_by_full_name(authors_name)
    if author_id <= 0:
        print("Author not found.")
        return False
    with get_connection() as connection:
        with connection.cursor() as cursor:
            cursor.execute(f"""INSERT INTO `pythonBooksAndAuthors`.`book` (author_id, title, yearReleased, isRead) 
                              VALUES ({author_id}, %s, %s, 0)""", (book_title, year_released))
            connection.commit()
            return True
    # author_id = 0
    # connection.cursor().execute(f"INSERT INTO `pythonBooksAndAuthors`.`book` (`author_id`, `title`, `yearReleased`) "
    #                             f"VALUES ({author_id}, {book_title}, {year_released});")


def add_author(name, birth_year, country_name):
    with get_connection() as db:
        with db.cursor() as cursor:
            cursor.execute(
                'INSERT INTO `pythonBooksAndAuthors`.`author` (`name`, `birth`, `country`) VALUES (%(name)s, %(birth_year)s, %(country_name)s);',
                {'name': name, 'birth_year': birth_year, 'country_name': country_name})
            db.commit()


def query_author_id_by_name(name):
    with get_connection() as connection:
        with connection.cursor() as cursor:
            cursor.execute(f"SELECT id FROM author WHERE name LIKE %s;", ("%" + name + "%",))
            row = cursor.fetchall()
            # in case there are two people with the same name fetchall() does a better job than fetchone()
            return row[0][0] if len(row) == 1 else -1


def query_author_id_by_full_name(name):
    with get_connection() as connection:
        with connection.cursor() as cursor:
            cursor.execute("SELECT id FROM author WHERE name LIKE %s", (name,))
            row = cursor.fetchall()
            return row[0][0] if len(row) == 1 else -1


def query_book_id_by_its_full_title(book_title):
    with get_connection() as connection:
        with connection.cursor() as cursor:
            cursor.execute(f"SELECT id FROM book WHERE book.title LIKE %s", (book_title,))
            row = cursor.fetchall()
            # in case there are two people with the same name fetchall() does a better job than fetchone()
            return row[0][0] if len(row) == 1 else -1


# INSERT INTO `pythonbooksandauthors`.`author` (`name`, `birth`, `country`) VALUES ('Machado de Assis', 1839, 'Brazil');
# INSERT INTO `pythonbooksandauthors`.`author` (`name`, `birth`, `country`) VALUES ('Carlos Drummond de Andrade', 1902, 'Brazil');
# SELECT * FROM author;
# INSERT INTO `pythonbooksandauthors`.`book` (`author_id`, `title`, `yearReleased`) VALUES ('1', 'Dom Casmurro', '1899');
# INSERT INTO `pythonbooksandauthors`.`book` (`author_id`, `title`, `yearReleased`) VALUES ('1', 'Quincas Borba', '1891');
# INSERT INTO `pythonbooksandauthors`.`book` (`author_id`, `title`, `yearReleased`) VALUES ('2', 'A Rosa do Povo', '1945');
# SELECT * FROM book;


def get_all_authors():
    """
    returns a list in json format
    """
    with get_connection() as db:
        with db.cursor() as cursor:
            cursor.execute("SELECT name, birth, country FROM author")
            return [{'name': row[0], 'birth': row[1], 'country': row[2]} for row in cursor.fetchall()]


def get_all_books():
    """
    returns a list in json format
    """
    with get_connection() as db:
        with db.cursor() as cursor:
            cursor.execute("SELECT title, yearReleased, isRead FROM book")
            return [{'title': row[0], 'year_released': row[1], 'is_read': row[2]} for row in cursor.fetchall()]


def mark_book_as_read(book_to_be_read):
    book_id = query_book_id_by_its_full_title(book_to_be_read)
    with get_connection() as connection:
        with connection.cursor() as cursor:
            cursor.execute(f"UPDATE book SET isRead = 1 WHERE id = {book_id}")
            connection.commit()


def prompt_delete_book(book_title):
    book_id = query_book_id_by_its_full_title(book_title)
    with get_connection() as connection:
        with connection.cursor() as cursor:
            cursor.execute(f"DELETE FROM book WHERE id = {book_id}")
            connection.commit()
