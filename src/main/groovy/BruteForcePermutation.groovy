import utils.CommonStringUtil

/**
 * The strategy uses brute force to find out the shortest superstring
 *
 * ASSUMPTION: pairs must overlap by more than half of their lengths
 *             e.g 4 DNA string of 10 bases will have a superstring less than 20
 *
 * The algorithm:
 *      find all the permutations of the input DNA Strings,
 *      create a variable sss to save the shortestSuperstring
 *      loop through all permutations, construct the shortest superstring
 *          if this superstring is shorter than the current sss, update sss
 *      return sss
 *
 * For N DNA Strings with M bases
 * Time-Analysis: O(N! * M)
 *      finding all the permutations requires O(N! * M), once all permutations are found,
 *      simple go through all permutations to find the shortest superstring using linear search
 *
 * Space-analysis: O(N! * M)
 *      A list of length N! with M bases is needed to store all permutations
 */
class BruteForcePermutation implements SuperstringStrategy {

    @Override
    String findShortestSuperstring(List<String> dnaStrings) {
        int totalLength = dnaStrings.inject(0) { int totalLength, String dnaString ->
            return totalLength + dnaString.length()
        }
        int maxLength = totalLength / 2 - 1

        // loop through all permutations and find the shortest superstring
        return dnaStrings.permutations().inject("") { String shortestSuperstring, List<String> permutation ->

            // loop through all dna strings in a permutation,
            // for each iteration,
            //      create a new shortest superstring from previous superstring and current DNA string
            //      if superstring exceeds max length, simply return the current superstring with no future processing
            String superString = permutation.inject("") { String superString, String dnaString ->
                return superString.length() <= maxLength ? CommonStringUtil.findShortestSuperstring(superString, dnaString) : superString
            }

            boolean isSuperstringValid = superString.length() <= maxLength
            boolean isShortestStringFound = isSuperstringValid && (
                    !shortestSuperstring ||  superString.length() < shortestSuperstring.length())

            // return the new superstring if it is shorter than the existing shortest superstring
            return isShortestStringFound ? superString : shortestSuperstring
        }
    }
}
