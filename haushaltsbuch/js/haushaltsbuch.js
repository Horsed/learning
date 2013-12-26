$(document).ready(function() {
  var konten = [{name:'Girokonto'}, {name:'TG Martin'}, {name:'TG Svenja'}];
  
  $.getJSON('/' + konten[0].name).always(function(data) {
    try {konten[0].buchungen = JSON.parse(data);} catch(e) {konten[0].buchungen = [];};
    $.getJSON('/' + konten[1].name).always(function(data) {
      try {konten[1].buchungen = JSON.parse(data);} catch(e) {konten[1].buchungen = [];};
      $.getJSON('/' + konten[2].name).always(function(data) {
        try {konten[2].buchungen = JSON.parse(data);} catch(e) {konten[2].buchungen = [];};
        
        initKonten(konten);
      });
    });
  });
});

function initKonten(konten) {
  var accounts = new Ractive({
    debug: true,
    el: 'page',
    template: '#accounts',
    append: true,
    data: {
      konten: konten,
      sum: sum
    },
    complete: function() {initSlider(); initNav(); applyDatepicker();}
  });
  
  accounts.on('new buchung', function(event) {
    event.context.buchungen.splice(0, 0, buchung());
    accounts.update();
  });
  accounts.on('edit buchung', function(event) {
    event.context.edit=true;
    accounts.update();
    applyDatepicker();
  });
  accounts.on('save buchung', function(event) {
    var konto = accounts.get('konten')[event.index.konto];
    event.context.edit=false;
    accounts.update();
    saveKonto(konto);
  });
  accounts.on('delete buchung', function(event) {
    var konto = accounts.get('konten')[event.index.konto];
    konto.buchungen.splice(event.index.buchung, 1);
    accounts.update();
    saveKonto(konto);
  });
}

function sum(buchungen) {
  var sum = 0;
  for(var i = 0, len = buchungen.length; i < len; i++) {
    var amount = parseFloat(buchungen[i].amount);
    sum += isNaN(amount) ? 0 : amount;
  }
  return sum;
}

function initSlider() {
  var slider = document.getElementById('slider'),
      bullets = $('.konto-link');
  window.mySwipe = Swipe(slider, {
    continuous: true,
    callback: function(pos) {
      var i = bullets.length;
      while (i--) {
        $(bullets[i]).removeClass('active');
      }
      $(bullets[pos]).addClass('active');
    }
  });
}

function initNav() {
  $('.konto-link').each(function(i) {
    $(this).click(function() {
      mySwipe.slide(i);
    });
  });
}

function applyDatepicker() {
  $('input[name=date]').each(function(){
    $(this).datepicker({
      format: 'dd.mm.yyyy'
    }).on('changeDate', function(event) {
      $(this).trigger('focus');
    });
  });
}

function buchung() {
  var d = moment().format('DD.MM.YYYY');
  return $.extend(true, {}, {description: '', amount:'', date: d, edit: true});
}

function saveKonto(konto) {
  var d = {key: konto.name, value: JSON.stringify(konto.buchungen)};
  $.ajax({
    type : 'POST',
    url : '/',
    dataType : 'json',
    data: d
  });
}