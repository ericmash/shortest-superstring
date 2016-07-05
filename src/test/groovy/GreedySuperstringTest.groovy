/*
 * Tests for GreedySuperstring
 */

import spock.lang.Specification
import utils.TestSampleUtil

class GreedySuperstringTest extends Specification{

    def 'Sample Dataset Test Case from Rosalind'() {
        setup:
        List<String> dnaStrings = ['ATTAGACCTG', 'CCTGCCGGAA', 'AGACCTGCCG', 'GCCGGAATAC']
        when:
        def result = new GreedySuperstring().findShortestSuperstring(dnaStrings)
        then:
        assert result == 'ATTAGACCTGCCGGAATAC'
    }

//    def 'Test cases from Driver Group'() {
//        setup:
//        List<String> dnaStrings = TestSampleUtil.instance.getDNAStrings()
//        when:
//        def result = new GreedySuperstring().findShortestSuperstring(dnaStrings)
//        then:
//        assert result.length() > 0 // simple make sure there is a result
//    }
}
