/*
 * Tests for BruteForceSuperstring
 */

import spock.lang.Specification
import utils.TestSampleUtil

class BruteForceSuperstringTest extends Specification{

    def 'Sample Dataset Test Case from Rosalind'() {
        setup:
        List<String> dnaStrings = ['ATTAGACCTG', 'CCTGCCGGAA', 'AGACCTGCCG', 'GCCGGAATAC']
        when:
        def result = new BruteForceSuperstring().findShortestSuperstring(dnaStrings)
        then:
        assert result == 'ATTAGACCTGCCGGAATAC'
    }

    /**
     * This test case never finishes running. It proves that brute force algorithm is too slow for the need of Driver Group based on given test case
     */
//    def 'Test cases from Driver Group'() {
//        setup:
//        List<String> dnaStrings = TestSampleUtil.instance.getDNAStrings()
//        when:
//        def result = new BruteForceSuperstring().findShortestSuperstring(dnaStrings)
//        then:
//        assert result.length() > 0 // simple make sure there is a result
//    }
}
