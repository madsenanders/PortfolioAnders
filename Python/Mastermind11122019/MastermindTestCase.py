import Mastermind
import unittest

class test_correct_results(unittest.TestCase):
    def test_solution_random(self):
        s = Mastermind.Solution()

        s.set_random_solution()

        self.assertTrue(len(s.get_code())==4)

    def test_wrong_all(self):
        s = Mastermind.Solution()
        g = Mastermind.Guess()
        f = Mastermind.Feedback()

        s.set_code('G')
        s.set_code('B')
        s.set_code('G')
        s.set_code('B')

        g.set_code('R')
        g.set_code('R')
        g.set_code('R')
        g.set_code('R')

        f.match(g.get_code(), s.get_code())

        self.assertEqual(f.get_joined_code(),  '____')

    def test_wrong_pos(self):
        s = Mastermind.Solution()
        g = Mastermind.Guess()
        f = Mastermind.Feedback()

        s.set_code('G')
        s.set_code('B')
        s.set_code('G')
        s.set_code('B')

        g.set_code('B')
        g.set_code('G')
        g.set_code('B')
        g.set_code('G')

        f.match(g.get_code(), s.get_code())

        self.assertEqual(f.get_joined_code(), 'WWWW')

    def test_right_all(self):
        s = Mastermind.Solution()
        g = Mastermind.Guess()
        f = Mastermind.Feedback()

        s.set_code('G')
        s.set_code('B')
        s.set_code('G')
        s.set_code('B')

        g.set_code('G')
        g.set_code('B')
        g.set_code('G')
        g.set_code('B')

        f.match(g.get_code(), s.get_code())

        self.assertEqual(f.get_joined_code(), 'BBBB')

    def test_half_right(self):
        s = Mastermind.Solution()
        g = Mastermind.Guess()
        f = Mastermind.Feedback()

        s.set_code('G')
        s.set_code('B')
        s.set_code('G')
        s.set_code('B')

        g.set_code('G')
        g.set_code('B')
        g.set_code('G')
        g.set_code('R')

        f.match(g.get_code(), s.get_code())

        self.assertEqual(f.get_joined_code(),'BBB_')

    def test_two_right(self):
        s = Mastermind.Solution()
        g = Mastermind.Guess()
        f = Mastermind.Feedback()

        s.set_code('G')
        s.set_code('B')
        s.set_code('G')
        s.set_code('B')

        g.set_code('G')
        g.set_code('Y')
        g.set_code('G')
        g.set_code('Y')

        f.match(g.get_code(), s.get_code())

        self.assertEqual(f.get_joined_code(),'BB__')

    def test_white(self):
        s = Mastermind.Solution()
        g = Mastermind.Guess()
        f = Mastermind.Feedback()

        s.set_code('Y')
        s.set_code('Y')
        s.set_code('Y')
        s.set_code('Y')

        g.set_code('Y')
        g.set_code('R')
        g.set_code('R')
        g.set_code('R')

        f.match(g.get_code(), s.get_code())
        self.assertEqual(f.get_joined_code(),'B___')

    def test_white2(self):
        s = Mastermind.Solution()
        g = Mastermind.Guess()
        f = Mastermind.Feedback()

        s.set_code('R')
        s.set_code('Y')
        s.set_code('Y')
        s.set_code('Y')

        g.set_code('R')
        g.set_code('R')
        g.set_code('R')
        g.set_code('R')

        f.match(g.get_code(), s.get_code())
        self.assertEqual(f.get_joined_code(),'B___')

if __name__ == "__main__":
    unittest.main()
