package utils

import spock.lang.Specification

/**
 * Test for OverlapGraphUtil
 */
class OverlapGraphUtilTest extends Specification {

    def 'Test getOverlapGraph'() {
        setup:
        List<String> inputs = ['OrangeApple', 'AppleBanana', 'AppleOrange', 'BananaPear']
        when:
        def result = OverlapGraphUtil.getOverlapGraph(inputs)
        then:
        // note that groovy compares if the keys and values are the same
        assert result == [
                'OrangeApple': [ 'AppleBanana': 5, 'AppleOrange': 5 ],
                'AppleBanana': [ 'BananaPear' : 6 ],
                'AppleOrange': [ 'OrangeApple': 6 ],
                'BananaPear' : [:] // no overlaps found
        ]
    }

    def 'Test getLongestOverlapPair'() {
        setup:
        def inputs = [
                'OrangeApple': [ 'AppleBanana': 5, 'AppleOrange': 5 ],
                'AppleBanana': [ 'BananaPear' : 6 ],
                'AppleOrange': [ 'OrangeApple': 6 ],
                'BananaPear' : [:]]
        when:
        def result = OverlapGraphUtil.getLongestOverlapPair(inputs)
        then:
        assert result.key == 'AppleBanana' && result.value == 'BananaPear'
    }

    def 'Test getLongestOverlapPair with no result'() {
        setup:
        def inputs = ['BananaPear' : [:]]
        when:
        def result = OverlapGraphUtil.getLongestOverlapPair(inputs)
        then:
        assert result == null
    }

    def 'Test getLongestOverlapPair with no overlap'() {
        setup:
        def inputs = [
                'AppleOrange': [:],
                'BananaPear' : [:]]
        when:
        def result = OverlapGraphUtil.getLongestOverlapPair(inputs)
        then:
        assert result.key == 'AppleOrange' && result.value == 'BananaPear'
    }

    def 'Test merge'() {
        setup:
        def inputs = [
                'OrangeApple': [ 'AppleBanana': 5, 'AppleOrange': 5 ],
                'AppleBanana': [ 'BananaPear' : 6 ],
                'AppleOrange': [ 'OrangeApple': 6 ],
                'BananaPear' : [:]]
        when:
        def result = OverlapGraphUtil.merge(inputs, 'AppleBanana', 'BananaPear')
        then:
        assert result == [
            'OrangeApple'     : [ 'AppleOrange': 5, 'AppleBananaPear': 5 ],
            'AppleOrange'     : [ 'OrangeApple': 6 ],
            'AppleBananaPear' : [:]
        ]
    }

    def 'Test merge with no overlap'() {
        setup:
        def inputs = [
                'OrangeApple': [:],
                'BananaPear' : [:]]
        when:
        def result = OverlapGraphUtil.merge(inputs, 'OrangeApple', 'BananaPear')
        then:
        assert result == [
                'OrangeAppleBananaPear' : [:]
        ]
    }
}
