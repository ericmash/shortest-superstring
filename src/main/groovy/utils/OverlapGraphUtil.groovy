package utils

/**
 * Util class to create and manupulate overlap graphs
 */
class OverlapGraphUtil {

    /**
     * Generate graph of overlap length of the input strings
     *
     * The result is a map of dna string to the length of longest common string with all other dna strings
     * ['ACTG' : [
     *      'ACTGACTG' : 4,
     *      'CTGACTG' : 3 ]]
     *
     * NOTE that only non-zero overlap will be stored
     *
     * @param strings
     * @return
     */
    static Map<String, Map<String, Integer>> getOverlapGraph(List<String> strings) {
        Map<String, Map<String, Integer>> longestCommonStringMap = [:]

        // loop through all dna strings, find the length of the longest common string
        strings.each { String leading ->
            Map<String, Integer> overlapMap = [:]
            longestCommonStringMap.put(leading, overlapMap)

            strings.each { String trailing ->
                if (trailing == leading) {
                    return
                }

                int overlap = CommonStringUtil.countLongestCommonString(leading, trailing)

                // only insert non-zero overlap to the map
                if (overlap > 0) {
                    overlapMap.put(trailing, overlap)
                }
            }
        }

        return longestCommonStringMap
    }

    /**
     * Find the longest overlap from the graph
     *
     * If more than 2 longest overlaps are found, the first one will be returned
     * Pair with zero overlap will be included in the result
     *
     * @param overlapGraph Overlap graph
     * @return map entry of the longest overlap pair (leading: trailing), return null if not found
     */
    static MapEntry getLongestOverlapPair(Map<String, Map<String, Integer>> overlapGraph) {
        if (overlapGraph.size() <= 1) { // there cannot be any overlap for only one string
            return null
        }

        MapEntry result = new MapEntry(null, null)
        int maxOverlap = 0

        // tranverse through the map, find the longest overlap
        overlapGraph.each { String leading, Map<String, Integer> trailings ->
            trailings.each { String trailing, Integer overlap ->
                if (overlap > maxOverlap) {
                    maxOverlap = overlap
                    result.key = leading
                    result.value = trailing
                }
            }
        }

        if (maxOverlap == 0) { // if no overlap found, randomly return 2 of the remaining strings
            Set<String> remainings = overlapGraph.keySet()
            result.key = remainings[0]
            result.value = remainings[1]
        }

        // return null if no overlap is found
        return result.key == null ? null : result
    }

    /**
     * Form the superstring from the input strings and update the overlap graph
     *
     * The distance between the new superstring and existing string will be calculated
     * and all references to the strings that get merged will be removed
     *
     * @param overlapGraph Overlap graph
     * @param head leading string to be merged
     * @param tail trailing string to be merged
     * @return updated overlap graph with the new superstring information
     */
    static Map<String, Map<String, Integer>> merge(Map<String, Map<String, Integer>> overlapGraph, String head, String tail) {
        if (!head || !tail) {
            throw new IllegalArgumentException('Input strings cannot be empty')
        }

        if (!overlapGraph.containsKey(head) || !overlapGraph.containsKey(tail)) {
            throw new IllegalArgumentException('Input string cannot be found in overlap graph')
        }

        // ?. operate will automatically bypass the operation if input is null and return null
        int overlap = overlapGraph.get(head)?.get(tail) ?: 0
        String mergeString = head + tail.substring(overlap)

        // remove all the references of the original strings
        overlapGraph.remove(head)
        overlapGraph.remove(tail)
        overlapGraph.each { String leading, Map trailings ->
            trailings.remove(head)
            trailings.remove(tail)

            // find overlap if mergeString is the tail
            int overlapLength = CommonStringUtil.countLongestCommonString(leading, mergeString)
            if (overlapLength > 0) {
                trailings.put(mergeString, overlapLength)
            }
        }

        // find overlaps if mergeString is the head
        Map<String, Integer> mergeOverlapMap = [:]
        overlapGraph.keySet().each { String trailing ->
            int overlapLength = CommonStringUtil.countLongestCommonString(mergeString, trailing)
            if (overlapLength > 0) {
                mergeOverlapMap.put(trailing, overlapLength)
            }
        }
        overlapGraph.put(mergeString, mergeOverlapMap)

        return overlapGraph
    }
}
