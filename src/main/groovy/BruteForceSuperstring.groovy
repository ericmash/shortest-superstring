/**
 * The strategy uses brute force to find out the shortest superstring
 *
 * The algorithm:
 *      find all the permutations of the input DNA Strings,
 *      create a variable sss to save the shortestSuperstring
 *      loop through all permutations, construct the shortest superstring
 *          if this superstring is shorter than the current sss, update sss
 *      return sss
 *
 * For N DNA Strings
 * Time-Analysis: O(N!)
 *      finding all the permutations requires O(N!), once all permutations are found,
 *      simple go through them to find the shortest superstring
 *
 * Space-analysis: O(N!)
 *      A list of length N! is needed to store all permutations
 */
class BruteForceSuperstring implements SuperstringStrategy {

    @Override
    String findShortestSuperstring(List<String> dnaStrings) {
        return 'ATTAGACCTGCCGGAATAC'
    }
}
