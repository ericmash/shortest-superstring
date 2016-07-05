import utils.CommonStringUtil

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
 * For N DNA Strings with M bases
 * Time-Analysis: O(N! * MlogM)
 *      finding all the permutations requires O(N! * MlogM), once all permutations are found,
 *      simple go through all permutations to find the shortest superstring using binary search
 *
 * Space-analysis: O(N! * M)
 *      A list of length N! with M bases is needed to store all permutations
 */
class BruteForceSuperstring implements SuperstringStrategy {

    @Override
    String findShortestSuperstring(List<String> dnaStrings) {
        return dnaStrings.permutations().inject("") { String shortestSuperstring, List<String> permutation ->

            String superString = permutation.inject("") { String superString, String dnaString ->
                return CommonStringUtil.findShortestSuperstring(superString, dnaString)
            }

            boolean isShortestStringFound = !shortestSuperstring || superString.length() < shortestSuperstring.length()

            return isShortestStringFound ? superString : shortestSuperstring
        }
    }
}
