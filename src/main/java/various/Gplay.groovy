package various

class Gplay {

    static main(args) {
        def rnd = new Random()
		(1..10).each { println rnd.nextInt(10) }
    }

}