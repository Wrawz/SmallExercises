"""
This file is for me to practice dealing with MySQL (which I'm used to dealing with) and SQLite3.
The only part I've coded inside this file was the MySQL part.
"""

# pip install mysql-connector-python
import re
import sqlite3
import mysql.connector
from mysql.connector import errorcode
from utils.database_connection import SQLite3DbConnection, MySQLDbConnection

"""Concerned with storing and retrieving books from a database"""


# SQLite3 from now on


def add_book_sqlite3(book_title, author):
    with SQLite3DbConnection("data.db") as db:
        with db.cursor() as cursor:
            cursor.execute("INSERT INTO book VALUES (?, ?, 0)", (book_title, author))
            # in SQLite3, the question mark works the same way as prepared statements,
            # but it doesn't seem to happen the same way when it comes to MySQL


def create_book_table_sqlite3():
    with SQLite3DbConnection('data.db') as db:
        with db.cursor() as cursor:
            cursor.execute(
                "CREATE TABLE IF NOT EXISTS book(name TEXT PRIMARY KEY NOT NULL, author TEXT, is_read INTEGER)")


def get_all_books_sqlite3():
    with SQLite3DbConnection("data.db") as db:
        cursor = db.cursor()
        cursor.execute("SELECT * FROM book")
        # books = cursor.fetchall()
        return [{'title': row[0], 'author': row[1], 'is_read': row[2]} for row in cursor.fetchall()]


# MySQL from now on

def create_book_tables():
    with MySQLDbConnection() as connection:
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
    with MySQLDbConnection() as connection:
        with connection.cursor() as cursor:
            cursor.execute("""INSERT INTO `pythonBooksAndAuthors`.`book` (author_id, title, yearReleased, isRead) 
                              VALUES (%s, %s, %s, 0)""", (author_id, book_title, year_released))
            return True


def add_author(name, birth_year, country_name):
    with MySQLDbConnection() as db:
        with db.cursor() as cursor:
            cursor.execute(
                'INSERT INTO `pythonBooksAndAuthors`.`author` (`name`, `birth`, `country`) VALUES (%(name)s, %(birth_year)s, %(country_name)s);',
                {'name': name, 'birth_year': birth_year, 'country_name': country_name})


def query_author_id_by_name(name):
    with MySQLDbConnection() as connection:
        with connection.cursor() as cursor:
            cursor.execute("SELECT id FROM author WHERE name LIKE %s;", ("%" + name + "%",))
            row = cursor.fetchall()
            # in case there are two people with the same name fetchall() does a better job than fetchone()
            return row[0][0] if len(row) == 1 else -1


def query_author_id_by_full_name(name):
    with MySQLDbConnection() as connection:
        with connection.cursor() as cursor:
            cursor.execute("SELECT id FROM author WHERE name LIKE %s", (name,))
            row = cursor.fetchall()
            return row[0][0] if len(row) == 1 else -1


def query_book_id_by_its_full_title(book_title):
    with MySQLDbConnection() as connection:
        with connection.cursor() as cursor:
            cursor.execute("SELECT id FROM book WHERE book.title LIKE %s", (book_title,))
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
    with MySQLDbConnection as db:
        with db.cursor() as cursor:
            cursor.execute("SELECT name, birth, country FROM author")
            return [{'name': row[0], 'birth': row[1], 'country': row[2]} for row in cursor.fetchall()]


def get_all_books():
    """
    returns a list in json format
    """
    with MySQLDbConnection() as db:
        with db.cursor() as cursor:
            cursor.execute("SELECT title, yearReleased, isRead FROM book")
            return [{'title': row[0], 'year_released': row[1], 'is_read': row[2]} for row in cursor.fetchall()]


def mark_book_as_read(book_to_be_read):
    book_id = query_book_id_by_its_full_title(book_to_be_read)
    with MySQLDbConnection() as connection:
        with connection.cursor() as cursor:
            cursor.execute("UPDATE book SET isRead = 1 WHERE id = %s", (book_id,))


def prompt_delete_book(book_title):
    book_id = query_book_id_by_its_full_title(book_title)
    with MySQLDbConnection() as connection:
        with connection.cursor() as cursor:
            cursor.execute("DELETE FROM book WHERE id = %s", (book_id,))
