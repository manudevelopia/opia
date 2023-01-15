package info.developia.opia.compiler

import spock.lang.Specification

class CompilerTest extends Specification {
    Compiler compiler = new Compiler()

    def "should compile the opia code"() {
        given:
        String filename = '/Users/m4nu/Development/github/opia/compiler/src/test/resources/HelloWord.opia'
        when:
        compiler.compile(filename)
        then:
        noExceptionThrown()
    }

    def "should compile"() {
        given:
        String code =
                """HelloWorld {
                    start {
                        print "hello world!"
                    }
                }"""
        when:
        compiler.compile(code)
        then:
        noExceptionThrown()
    }
}
