import unittest
from kosaraju import *


class TestSCC(unittest.TestCase):
    leader = {}
    finish = {}
    explored = set()
    s = 0
    t = 0
    def setUp(self):
        self.g = {1:[2], 2:[3], 3:[1], 4:[2,5], 5:[6], 6:[4]}
        self.g_rev = {1:[3], 2:[1,4], 3:[2], 4:[6], 5:[4], 6:[5]}

    def test_upper(self):
        self.assertEqual('foo'.upper(), 'FOO')

    def test_isupper(self):
        self.assertTrue('FOO'.isupper())
        self.assertFalse('Foo'.isupper())

    def test_split(self):
        s = 'hello world'
        self.assertEqual(s.split(), ['hello', 'world'])
        # check that s.split fails when the separator is not a string
        with self.assertRaises(TypeError):
            s.split(2)

    def test_dfs_loop(self):

        dfs_loop(self.g_rev)
        dfs_loop(self.g)
        self.assertFalse('Foo'.isupper())



if __name__ == '__main__':

    unittest.main()