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
     * @return shortest common superstring
     */
    static String findShortestSuperstring(String a, String b) {
        int lcsLength = countLongestCommonString(a, b)
        return a.concat(b.substring(lcsLength, b.length()))
    }

    /**
     * Find the length of the longest common string from the two input strings
     * NOTE that the strings are compared in order,
     * "OrangeApple" + "AppleOrange" -> "Apple" -> 5
     *
     * Binary search will be used to achieve O(NlogN) for string with N characters
     * The upper bound is searched first so that the lower can be skipped in a common string is found
     *
     * Return 0 if no common string is found
     *
     * @param a first string
     * @param b second string
     * @return length of longest common string
     */
    static int countLongestCommonString(String a, String b) {
        return countLCS(a, b, 0, Math.min(a.length(), b.length()), 0)
    }


    /**
     * Find the length of the longest common string from the two input strings
     * NOTE that the strings are compared in order
     *
     * Return 0 if no common string is found
     *
     * @param a first string
     * @param b second string
     * @param minLength minimum length to check for common string
     * @param maxLength maximum length to check for common string
     * @param maxFound maximum length of common string that has been found
     * @return length of longest common string
     */
    static private int countLCS(String a, String b, int minLength, int maxLength, int maxFound) {
        if (!a || !b || a.isEmpty() || b.isEmpty()) {
            return 0
        }

        if (maxLength <= maxFound) { // no further check is needed since the max length is already found
            return maxFound
        }

        if (minLength > maxLength) { // check is completed, return the max length found
            return maxFound
        }

        int lengthToCheck = (minLength + maxLength + 1) / 2

        int aLength = a.length()
        int bLength = b.length()

        // there is no common string if the length that is being checked is longer than one of the input strings
        if (lengthToCheck > aLength || lengthToCheck > bLength || lengthToCheck < 1) {
            return maxFound
        }

        int aStartIndex = aLength - lengthToCheck
        int aEndIndex = aLength
        int bEndIndex = lengthToCheck

        boolean isCommonStringFound = a.substring(aStartIndex, aEndIndex) == b.substring(0, bEndIndex)
        int currMaxFound = isCommonStringFound ? lengthToCheck : maxFound

        if (isCommonStringFound) { // try to find longer common string
            return countLCS(a, b, currMaxFound + 1, maxLength, currMaxFound)
        } else {
            // check upper bound first, then lower bound if no common string is found in upper bound
            currMaxFound = countLCS(a, b, lengthToCheck + 1, maxLength, currMaxFound)
            return countLCS(a, b, minLength, lengthToCheck - 1, currMaxFound)
        }
    }
}
