"""
Largest Range

Write a function that takes in an array of integers and returns an array of length 2
representing the largest range of numbers contained in that array. The first number
in the output array should be the first number in the range while the second number
should be the last in the range. A range of numbers is defined as a set of numbers
that come right after each other in the set of real integers. For instance, the output
array [2, 6] represents the range [2, 3, 4, 5, 6], which is a range of length 5. Note
that the numbers do not need to be ordered or adjacent in the array in order to form
a range. Assume that there will only be one largest range.

Sample input: [1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6]
Sample output: [0, 7]
"""

#!/usr/bin/env python3
from typing import Tuple, List


def largest_range(array: List[int]) -> Tuple[int, int]:
    # write your code here.
    arr = [x for x in array]
    for i in range(len(arr) - 1):
        for j in range(i + 1, len(arr)):
            if arr[i] > arr[j]:
                temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
    pairs = []
    differences = []
    for i in range(len(arr) - 1):
        first = arr[i]
        for j in range(i, len(arr) - 1):
            if arr[j] + 1 == arr[j + 1]:
                continue
            else:
                pairs.append((first, arr[j]))
                break
    for pair in pairs:
        x, y = pair
        difference = y - x
        differences.append(difference)
    biggest_difference = differences.index(max(differences))
    return pairs[biggest_difference]


if __name__ == '__main__':
    print(largest_range([1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6]))  # output: [0, 7]
