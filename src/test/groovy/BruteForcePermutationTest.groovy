/*
 * Tests for BruteForcePermutation
 */

import spock.lang.Specification
import utils.TestSampleUtil

class BruteForcePermutationTest extends Specification{

    def 'Test Sample Dataset from Rosalind'() {
        setup:
        List<String> dnaStrings = ['ATTAGACCTG', 'CCTGCCGGAA', 'AGACCTGCCG', 'GCCGGAATAC']
        when:
        def result = new BruteForcePermutation().findShortestSuperstring(dnaStrings)
        then:
        assert result == 'ATTAGACCTGCCGGAATAC'
    }

    def 'Test Dataset with and without overlaps'() {
        setup:
        List<String> dnaStrings = ['AAAAAAAA', 'AAAAAAAA', 'AAAAAAAA', 'AAAAAAAA', 'TTTTTTTT']
        when:
        def result = new BruteForcePermutation().findShortestSuperstring(dnaStrings)
        then:
        assert result == 'AAAAAAAATTTTTTTT'
    }

//    /**
//     * This test case never finishes running. It proves that brute force algorithm is too slow for the need of Driver Group based on given test case
//     */
//    def 'Test cases from Driver Group'() {
//        setup:
//        List<String> dnaStrings = TestSampleUtil.instance.getDNAStrings()
//        when:
//        def result = new BruteForcePermutation().findShortestSuperstring(dnaStrings)
//        then:
//        assert result.length() > 0 // simple make sure there is a result
//    }
}
