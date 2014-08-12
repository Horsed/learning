package org.horsed.rxcamel

import rx.Observable

static filter(item) {
	item.id > 1
}

static map(item) {
	item.value*item.value
}

Observable.from([
	[id: 1, value: 12],
	[id: 2, value: 13]
])
.filter(Rx.&filter)
.map(Rx.&map)
.subscribe({ println(it) })