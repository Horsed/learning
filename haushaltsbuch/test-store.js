var expect = require('expect.js');

describe('store', function(){
  beforeEach(function() {
    store = require('./store.js').store;
  });
  
  it('should not have specific item', function(done) {
    expect(store.get('key')).to.eql({});
    done();
  });
  
  it('should not have any items', function(done) {
    expect(store.get()).to.eql({});
    done();
  });
  
  it('should deliver previously saved item', function(done) {
    store.save('key', 'value');
    
    expect(store.get('key')).to.equal('value');
    done();
  });
  
  it('should deliver all previously saved items', function(done) {
    store.save('key', 'value');
    
    expect(store.get()).to.eql({'key': 'value'});
    done();
  });
  
  it('should not deliver removed item', function(done) {
    store.save('key', 'value');
    store.remove('key');
    
    expect(store.get('key')).to.eql({});
    done();
  });
});