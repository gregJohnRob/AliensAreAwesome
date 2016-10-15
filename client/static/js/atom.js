var number_correct = 0;
$( init );

function getRandomInt(min, max) {
  var ran = Math.random();
  if (ran > 0.99) {
    ran = 0.95;
  }
  return Math.floor(ran * (max - min)) + min;
}
 
function init() {
 
  // Hide the success message
  $('#successMessage').hide();
 
  // Reset the game
  number_correct = 0;
  $('#atom_home').html( '' );
  $('#atom_destination').html( '<div class="magic_line magic_line_first"> </div> <div class="magic_line magic_line_second"> </div> <div class="magic_line magic_line_third"> </div> <div class="magic_line magic_line_fourth"> </div>' );
 
  // Create the pile of shuffled cards
  var atoms_h20 = [ ["H", "O", "H"], "Water" ];
  var atoms_co2 = [ ["O", "C", "O" ], "Carbon Dioxide" ] ;
  var atoms_ch4 = [ ["H", "H", "C", "H", "H"], "Methane" ];
  var atoms_n20 = [ ["N", "N", "O"], "Nitrous Oxide" ];

  var array_of_molecules = [ atoms_h20[0], atoms_co2[0], atoms_ch4[0], atoms_n20[0] ];
  var array_of_molecules_names = [ atoms_h20[1], atoms_co2[1], atoms_ch4[1], atoms_n20[1] ];

  molecule_choice = getRandomInt(0,4);

  atoms_ordered = array_of_molecules[molecule_choice];
  atoms_random = atoms_ordered.slice().sort( function() { return Math.random() - .5 } );
  name_of_molecule = array_of_molecules_names[molecule_choice];

  $("#text_for_question").text("Try and make a " + name_of_molecule + " molecule.");


  // where the atoms start
  for ( var i=0; i<atoms_random.length; i++ ) {
    $('<div>' + atoms_random[i] + '</div>').data( 'atom', atoms_random[i] ).attr( 'id', 'card'+atoms_random[i] ).appendTo( '#atom_home' ).draggable( {
      containment: '#content',
      stack: '#atom_home div',
      cursor: 'move',
      revert: true
    } );
  }
 
  // where the atoms should go
  if (atoms_ordered.length === 3) {
    $('#atom_destination').height(220);
    for ( var i=0; i<atoms_ordered.length; i++ ) {
      if (i === 0) {
        $('<div class="atom_dest" style="margin: 80px 0 0 0;"> </div>').data( 'atom', atoms_ordered[i] ).appendTo( '#atom_destination' ).droppable( {
          accept: '#atom_home div',
          hoverClass: 'hovered',
          drop: handleCardDrop
        } );
      } else if (i === 1) {
        $('<div class="atom_dest" style="margin: 0 0 0 120px;"> </div>').data( 'atom', atoms_ordered[i] ).appendTo( '#atom_destination' ).droppable( {
          accept: '#atom_home div',
          hoverClass: 'hovered',
          drop: handleCardDrop
        } );
      } else if (i === 2) {
        $('<div class="atom_dest" style="margin: 80px 0 0 240px;"> </div>').data( 'atom', atoms_ordered[i] ).appendTo( '#atom_destination' ).droppable( {
          accept: '#atom_home div',
          hoverClass: 'hovered',
          drop: handleCardDrop
        } );
      }
      $('.magic_line_first').css({'-webkit-transform': 'rotate(55deg)', 
                                  '-moz-transform': 'rotate(55deg)', 
                                  '-o-transform': 'rotate(55deg)', 
                                  '-ms-transform': 'rotate(55deg)', 
                                  'transform': 'rotate(55deg)', 
                                  'margin': '72px 0 0 126px',
                                  'height': '47px' });
      $('.magic_line_second').css({'-webkit-transform': 'rotate(305deg)', 
                                  '-moz-transform': 'rotate(305deg)', 
                                  '-o-transform': 'rotate(305deg)', 
                                  '-ms-transform': 'rotate(305deg)', 
                                  'transform': 'rotate(305deg)', 
                                  'margin': '72px 0 0 214px',
                                  'height': '47px' });
      $('.magic_line_third').css({'display': 'none'});
      $('.magic_line_fourth').css({'display': 'none'});


    }
  } else if (atoms_ordered.length === 5) {
    $('#atom_destination').height(400);
    for ( var i=0; i<atoms_ordered.length; i++ ) {
      if (i === 0) {
        $('<div class="atom_dest" style="margin: 200px 0 0 0;"> </div>').data( 'atom', atoms_ordered[i] ).appendTo( '#atom_destination' ).droppable( {
          accept: '#atom_home div',
          hoverClass: 'hovered',
          drop: handleCardDrop
        } );
      } else if (i === 1) {
        $('<div class="atom_dest" style="margin: 0 0 0 120px;"> </div>').data( 'atom', atoms_ordered[i] ).appendTo( '#atom_destination' ).droppable( {
          accept: '#atom_home div',
          hoverClass: 'hovered',
          drop: handleCardDrop
        } );
      } else if (i === 2) {
        $('<div class="atom_dest" style="margin: 130px 0 0 120px;"> </div>').data( 'atom', atoms_ordered[i] ).appendTo( '#atom_destination' ).droppable( {
          accept: '#atom_home div',
          hoverClass: 'hovered',
          drop: handleCardDrop
        } );
      } else if (i === 3) {
        $('<div class="atom_dest" style="margin: 260px 0 0 120px;"> </div>').data( 'atom', atoms_ordered[i] ).appendTo( '#atom_destination' ).droppable( {
          accept: '#atom_home div',
          hoverClass: 'hovered',
          drop: handleCardDrop
        } );
      } else if (i === 4) {
        $('<div class="atom_dest" style="margin: 200px 0 0 240px;"> </div>').data( 'atom', atoms_ordered[i] ).appendTo( '#atom_destination' ).droppable( {
          accept: '#atom_home div',
          hoverClass: 'hovered',
          drop: handleCardDrop
        } );
      } 

      $('.magic_line_first').css({'margin': '98px 0 0 169px',
                                  'height': '32px'});
      $('.magic_line_second').css({'margin': '229px 0 0 169px',
                                  'height': '32px'});
      $('.magic_line_third').css({'-webkit-transform': 'rotate(55deg)', 
                                  '-moz-transform': 'rotate(55deg)', 
                                  '-o-transform': 'rotate(55deg)', 
                                  '-ms-transform': 'rotate(55deg)', 
                                  'transform': 'rotate(55deg)', 
                                  'margin': '202px 0 0 126px',
                                  'height': '45px' });
      $('.magic_line_fourth').css({'-webkit-transform': 'rotate(305deg)', 
                                  '-moz-transform': 'rotate(305deg)', 
                                  '-o-transform': 'rotate(305deg)', 
                                  '-ms-transform': 'rotate(305deg)', 
                                  'transform': 'rotate(305deg)', 
                                  'margin': '202px 0 0 214px',
                                  'height': '45px'});

    }
  }
  
 
}


function handleCardDrop( event, ui ) {
  var slotNumber = $(this).data( 'atom' );
  var cardNumber = ui.draggable.data( 'atom' );
 
  // If the card was dropped to the correct slot,
  // change the card colour, position it directly
  // on top of the slot, and prevent it being dragged
  // again
 
  if ( slotNumber === cardNumber ) {
    ui.draggable.addClass( 'correct' );
    ui.draggable.draggable( 'disable' );
    $(this).droppable( 'disable' );
    ui.draggable.position( { of: $(this), my: 'left top', at: 'left top' } );
    ui.draggable.draggable( 'option', 'revert', false );
    number_correct++;
  } 
   
  // If all the cards have been placed correctly then display a message
  // and reset the cards for another go
 
  if ( number_correct === atoms_ordered.length ) {
    $('#successMessage').show();
  }
 
}