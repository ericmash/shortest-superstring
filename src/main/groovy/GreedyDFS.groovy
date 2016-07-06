import utils.OverlapGraphUtil

/**
 * The strategy uses greedy mechanism to find out the shortest superstring.
 *
 * ASSUMPTION: pairs must overlap by more than half of their lengths
 *             e.g 4 DNA string of 10 bases will have a superstring less than 20
 *
 *
 * Reference: https://www.youtube.com/watch?v=aGpMH5l3mrI
 *
 * NOTE that this greedy algorithm is only an approximation. It may not return the "shortest" superstring if
 * overlap length of dna strings are the same
 *
 * POSSIBLE IMPROVEMENT:
 * Verify the result of the greedy solution, if the superstring does not satisfy the assumption (length < total length / 2)
 * Then revisit an alternative path that has the same overlap length
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
 * Time-analysis: O(N * (N choose 2) * M) where N * (N choose 2) is the time to find all combinations
 *                                            and M is the time to find longest superstring for each combination
 *
 * Space-analysis: O(N choose 2) to store all the combinations
 *
 */
class GreedyDFS implements SuperstringStrategy {

    @Override
    String findShortestSuperstring(List<String> dnaStrings) {

        Map<String, Map<String, Integer>> overlapGraph = OverlapGraphUtil.getOverlapGraph(dnaStrings)

        // keep merging until only 1 string left
        while (overlapGraph.size() > 1) {
            MapEntry pair = OverlapGraphUtil.getLongestOverlapPair(overlapGraph)

            OverlapGraphUtil.merge(overlapGraph, pair.key as String, pair.value as String)
        }

        // the remaining string is the shortest superstring
        return overlapGraph.keySet().first()
    }
}
