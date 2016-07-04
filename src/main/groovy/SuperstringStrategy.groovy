/**
 * Strategy interface to construct superstring
 */
interface SuperstringStrategy {

    /**
     * Construct the shortest superstring from the input list of DNA Strings
     *
     * @param dnaStrings List of DNA Strings containing only ATCG
     * @return the shortest superstring
     */
    String findShortestSuperstring(List<String> dnaStrings);
}