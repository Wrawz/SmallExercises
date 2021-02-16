import sqlite3
import mysql.connector
from mysql.connector import errorcode


class SQLite3DbConnection:
    def __init__(self, host):
        self.connection = None
        self.host = host

    def __enter__(self) -> sqlite3.Connection:
        self.connection = sqlite3.connect(self.host)
        return self.connection

    def __exit__(self, exc_type, exc_val, exc_tb):
        if exc_type or exc_val or exc_tb:
            self.connection.close()
        else:
            self.connection.commit()  # apparently, always committing is not a problem although not necessary
            self.connection.close()


class MySQLDbConnection:
    def __init__(self):
        self.connection = None

    def __enter__(self) -> mysql.connector.MySQLConnection:
        try:
            self.connection = mysql.connector.connect(user="root",
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
        return self.connection

    def __exit__(self, exc_type, exc_val, exc_tb):
        if exc_type or exc_val or exc_tb:
            self.connection.close()
        else:
            self.connection.commit()
            self.connection.close()
