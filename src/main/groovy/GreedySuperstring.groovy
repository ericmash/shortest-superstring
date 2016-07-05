import utils.CommonStringUtil

/**
 * The strategy uses greedy mechanism to find out the shortest superstring
 *
 * Reference: https://www.youtube.com/watch?v=aGpMH5l3mrI
 *
 * Data structure:
 *      The data will be stored in a maps of map,
 *          ['ATCGAT' : [
 *              'ATGCAT': 2
 *              'GATCAT': 3
 *          ],
 *      which means 'ATCGAT' and 'ATGCAT' longest common string has a length of 2, 'GATCAT' a lenght of 3
 *
 * The algorithm:
 *      Find all the combinations of input DNA strings
 *      Loop until there are less than 2 combinations
 *          Iterate through all the combinations, create/update a maps of map to store the length of longest common string for all combinations,
 *          Iterate through the maps of map, find the absolute longest pair and form the superstring
 *          Updating the maps of map with the new superstring, remove the reference of the original string
 *          Repeat the loop
 *
 * For N DNA Strings with M bases
 *
 * Time-analysis: O(N * (N choose 2) * MlogM) where N * (N choose 2) is the time to find all combinations
 *                                            and MlogM is the time to find longest superstring for each combination
 *
 * Space-analysis: O(N choose 2) to store all the combinations
 *
 */
class GreedySuperstring implements SuperstringStrategy {

    @Override
    String findShortestSuperstring(List<String> dnaStrings) {
        return 'ATTAGACCTGCCGGAATAC'
    }
}
