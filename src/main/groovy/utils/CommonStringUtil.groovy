package utils

/**
 * Util class for finding common string
 */
class CommonStringUtil {

    /**
     * Find the shortest common superstring of the two input strings
     *
     * "OrangeApple" + "AppleBanana" -> "OrangeAppleBanana"
     *
     * @param a first string
     * @param b second string
     * @return shortest common superstring,
     * null if shortest common superstring is not found or overlap is shorter than half of their lengths
     */
    static String findShortestSuperstring(String a, String b) {
        int lcsLength = countLongestCommonString(a, b)
        return a.concat(b.substring(lcsLength, b.length()))
    }

    /**
     * Find the length of the longest common string from the two input strings
     *
     * NOTE that the strings are compared in order,
     * "PearOrange" + "OrangePear" -> "Orange" -> 6
     * "OrangeApple" + "AppleOrange" -> 0 (because this is invalid based on assumption)
     *
     * Linear search will be used to achieve O(N) for string with N characters
     *
     * @param a first string
     * @param b second string
     * @return length of longest common string, 0 if no common string is found
     */
    static int countLongestCommonString(String a, String b) {
        int maxLength = Math.min(a.length(), b.length())

        // linear search with decreasing length
        for (int i = maxLength; i > 0; i --) {
            int aStartIndex = a.length() - i
            boolean isCommonStringFound = a.substring(aStartIndex, a.length()) == b.substring(0, i)

            if (isCommonStringFound) {
                return i
            }
        }

        return 0
    }
}
