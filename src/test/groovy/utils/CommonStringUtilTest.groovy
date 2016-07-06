/*
 * Tests for CommonStringUtil
 */

import spock.lang.Specification
import utils.CommonStringUtil

class CommonStringUtilTest extends Specification {

    def 'Test findShortestSuperString'() {
        setup:
        String a = 'OrangeApplePear'
        String b = 'ApplePearBanana'
        when:
        def result = CommonStringUtil.findShortestSuperstring(a, b)
        then:
        assert result == 'OrangeApplePearBanana'
    }

    def 'Test findShortestSuperString with repeated string'() {
        setup:
        String a = 'OrangeAppleAppleApple'
        String b = 'AppleAppleAppleOrange'
        when:
        def result = CommonStringUtil.findShortestSuperstring(a, b)
        then:
        assert result == 'OrangeAppleAppleAppleOrange'
    }

    def 'Test findShortestSuperString with empty string'() {
        setup:
        String a = ''
        String b = 'OrangeApple'
        when:
        def result = CommonStringUtil.findShortestSuperstring(a, b)
        then:
        assert result == 'OrangeApple'
    }

    def 'Test countLongestCommonString'() {
        setup:
        String a = 'OrangeApple'
        String b = 'AppleOrange'
        when:
        def result = CommonStringUtil.countLongestCommonString(a, b)
        then:
        assert result == 5
    }

    def 'Test countLongestCommonString with empty string'() {
        setup:
        String a = 'Orange'
        String b = ''
        when:
        def result = CommonStringUtil.countLongestCommonString(a, b)
        then:
        assert result == 0
    }

    def 'Test countLongestCommonString with entire string matched'() {
        setup:
        String a = 'Orange'
        String b = 'Orange'
        when:
        def result = CommonStringUtil.countLongestCommonString(a, b)
        then:
        assert result == 6
    }

    def 'Test countLongestCommonString with 2 matches'() {
        setup:
        String a = 'OrangeAppleStrawberry'
        String b = 'AppleStrawberryBanana'
        when:
        def result = CommonStringUtil.countLongestCommonString(a, b)
        then:
        assert result == 15
    }

    def 'Test countLongestCommonString with no match'() {
        setup:
        String a = 'AppleBanana'
        String b = 'StrawberryPineapple'
        when:
        def result = CommonStringUtil.countLongestCommonString(a, b)
        then:
        assert result == 0
    }
}
