"""
Practicing coding inside the terminal.
"""
#!/usr/bin/env python3
import re
import random
import argparse


def get_arguments() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument('-l', '--length', type=int, help='Numbers of characters of your password.')
    options = parser.parse_args()
    if not options.length:
        return 0
    if int(options.length) < 8:
        return -1
    return int(options.length)


def create_password(pass_length: int = 12) -> str:
    lower = 'qwertyuiopasdfghjklzxcvbnm'
    upper = lower.upper()
    numbers = '0123456789'
    special = '!@#$%^&*()-_=+[]{}'
    chars = lower + upper + numbers + special
    while True:
        password = ''
        for i in range(pass_length):
            password += chars[random.randint(0, len(chars)-1)]
        if re.match(".*[a-z].*", password) and re.match(".*[A-Z].*", password) and re.match(".*[0-9].*", password) and re.match(".*[!@#$%^&*\\(\\)-_=+\\[\\]\\{\\}].*", password):
            return password


if __name__ == '__main__':
    options = get_arguments()
    if options == 0:
        print(create_password())
    elif options == -1:
        print('Password length has to be 8 or higher.')
    else:
        print(create_password(options))
