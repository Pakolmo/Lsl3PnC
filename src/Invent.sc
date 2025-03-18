;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	INVENT.SC
;;;;	(c) Sierra On-Line, Inc, 1988
;;;;
;;;;	Author: Bob Heitman
;;;;
;;;;	Classes to manage inventory (items in the possession of other objects
;;;;	in an adventure game).
;;;;
;;;;	Classes:
;;;;		InvItem
;;;;		Inventory
;;;;
;;;;	Objects:
;;;;		invDialog


(script# INVENT)
(include game.sh)
(use Main)
(use Intrface)
(use Save)
(use System)
;(use Motion) ;add
;(use Jump) ;add
;(use rm540)
;(use Actor)
;(use Wander)

(local
	yesI
	selI
	lookI
	pigAppears
	[msgBuf 33]
	[titleBuf 22]
)

; a stock list will be able to handle the scanning required to:
; find items by parsed name (firstTrue: #saidMe:)
; build status dialog (first, next)
; find items in room (firstTrue: #ownedBy)
; reference items by number (at: enumName)



(class InvItem kindof Object
	;;; An InvItem is something which can be owned by an object in an
	;;; adventure game.

	(properties
		-info- $8004		;(| CLASSBIT NODISPLAY)
		name "InvI"			;my literal name
		said 0				;said spec which user can type to identify this item
		description 0		;long text description
		owner 0				;who owns this item
		view 0				;picture of the item
		loop 0
		cel 0
		script 0				;a script that can control the item
	)

;;;	(methods
;;;		saidMe				;does user input match the said spec?
;;;		ownedBy				;return TRUE if owned by given object
;;;		showSelf				;display this item
;;;		moveTo				;change ownership of this item
;;;		changeState
;;;	)



	(method (saidMe)
		;; Return TRUE if my "said" was "parsed"

		(return (Said said))
	)


	(method (ownedBy id)
		;; Return TRUE if owned by this ID.

		(return (== owner id))
	)
	

	(method (moveTo id)
		;; Set my "owner" to passed ID.

		(= owner id)
		(return self)
	)


	(method (showSelf)
		;; Display this object.

		(ShowView 
			(if description
				description
			else
				name
			)
			view loop cel
		)
	)


	(method (changeState newState)
		(if script
			(script changeState:newState)
		)
	)
)




(class Inventory kindof Set
	;;; This is the set of all inventory items in the game.

	(properties
		name "Inv"
;;;		carrying	"You are carrying:" ;ENGLISH
		carrying	"Llevas contigo:" ;SPANISH
			;Title of the inventory display when the object in question
			;has some inventory items.
;;;		empty "You are carrying nothing!" ;ENGLISH
		empty "^No llevas nada!" ;SPANISH
			;Title of the inventory display when the object in question
			;has no inventory items.
	)

;;;	(methods
;;;		showSelf			;display inventory owned by an object
;;;		saidMe			;return InvItem matching user input
;;;		ownedBy			;return InvItem owned by an object
;;;	)


	(method (init)
		(= inventory self)
	)


	(method (saidMe)
		;; Return the ID of the first item in the inventory whose said
		;; spec matches user input.

		(return (self firstTrue: #saidMe:))
	)


	(method (ownedBy whom)
		;; Return the first item in inventory which is owned by 'whom'.

		(return (self firstTrue: #ownedBy: whom))
	)


	(method (showSelf whom)
		;; Show the possessions of 'whom'.
	
		(invDialog text:carrying, doit:whom)
	)
)




(instance invDialog of Dialog
	(properties
		name "invD"
	)


	(method (init whom &tmp lastX lastY widest num el node obj)

		;Init positioning vars.
		(= widest (= lastX (= lastY MARGIN)))
		(= num 0)

		(for 	((= node (inventory first:)))
				node
				((= node (inventory next: node)))

			(= obj (NodeValue node))
	
			;Does this character own this thing.
			(if (obj ownedBy: whom)	
				(++ num)
				(self add: 
					((= el (DText new:))
						value: obj, 
						text: (obj name?), 
						nsLeft: lastX, 
						nsTop: lastY,
						state:(| dActive dExit),
						font: smallFont,
						setSize:,
						yourself:
					)
				)

				;Keep track of widest item.
				(if (< widest (- (el nsRight?) (el nsLeft?)))
					(=  widest (- (el nsRight?) (el nsLeft?)))
				)

				;Bump lastY by height of character this item.
				(+= lastY (+ (- (el nsBottom?) (el nsTop?)) 1))

				;Wrap to next column.
				(if (> lastY 140)
					(= lastY MARGIN)
					(+= lastX (+ widest 10))
					(= widest 0)
				)
			)
		)
 
		;If no items owned, bag it.
		(if (not num)
			(self dispose:)
			(return 0)
		)

		; give ourself the class SysWindow as our window
		(= window SysWindow)

		;Size dialog and add button to lower right
		(self setSize:)
		(= selI (DButton new:))
		(selI 
;;;			text: "Select", ;ENGLISH
			text: "Elegir", ;SPANISH
			setSize:,
			moveTo: 
				;(- nsRight (+ MARGIN (yesI nsRight?)))
				(+ nsLeft (+ MARGIN (selI nsLeft?)))
				nsBottom
		)
		(= lookI (DButton new:))
		(lookI 
;;;			text: "Look_", ;English
			text: "Mirar", ;Spanish
			setSize:,
			moveTo: 
				(+ nsLeft (+ MARGIN (selI nsRight?)))
				nsBottom
		)
		(= yesI (DButton new:))
		(yesI 
			text: "OK", 
			setSize:,
			moveTo: 
				(+ nsLeft (+ MARGIN (lookI nsRight?)))
				nsBottom
		)

		;Add button and resize the dialog.
		(self add: selI lookI yesI, setSize:, center:)

		(return num)
	)

	(method (doit whom &tmp el)
		;Initialize the dialog. If we have nothing, tell user.
		(if (not (self init: whom))
			(Print (inventory empty?))
			(return)
		)
	
		;Call the dialog with exit as default
		(self open: wTitled 15)
		(= el yesI)
		
		(theGame setCursor: 993) ;start with select arrow
		
		(repeat
			(= el (super doit:el))

			;These returns signal end of dialog
			(if (or (not el) (== el -1) (== el yesI))
				(if (== theCursor 993) 
					(= theCursor 999) ;if select change to walk on exit inv.
				)
				(break)
			)
			
			(if
				(or
					(== el selI)
					(== el lookI)
					(== el yesI) 
				)
				(cond 
					((== el selI)
						(if (!= theCursor 993)
							(theGame setCursor: 993)
						)
					)
					((== el lookI)
						(if (!= theCursor 998)
							(theGame setCursor: 998)
						)
					)
					(else
						;do nothing
					)
				)
			else	
				(switch theCursor
					(993 ;set cursor to selected item and print desc.
						(= itemIcon ((el value?) view?))
						(theGame setCursor: ((el value?) view?))
					)
					(998 ;look at item
						;((el value?) showSelf:)
					
					(if
						(and
							(== 10 ((el value?) view?)) ;Looked at divoce papers
							(not (ego has: iSpaKeycard)) ;does not have keycard
						)
						
						(ego get: iSpaKeycard)
						(theGame changeScore: 100)
						(Print 0 42)
						
					else

						((el value?) showSelf:) ;display the inventory item normally.

					)
					)
					(19 ;coco
							(if (== ((el value?) view?) 16) ;selected coco with bra
												(cond 
					((not (ego has: iBra))
						(Print 540 9)
					)
					((not (ego has: iCoconuts))
						(Print 540 10)
						(if (>= filthLevel 3)
							(Print 540 11
								#at -1 144
							)
						)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (Btst fRemovedBra))
						(Print 540 12)
						(Print 540 13
							#at -1 144
						)
					)
					((Btst fCoconutsInBra)
						(Print 540 14)
					)
					(else
						((ScriptID 540 1) changeState: 5)
					)
				)
			)
							
					)
					(16 ;bra
							(if (== ((el value?) view?) 19) ;selected bra with coco
												(cond 
					((not (ego has: iBra))
						(Print 540 9)
					)
					((not (ego has: iCoconuts))
						(Print 540 10)
						(if (>= filthLevel 3)
							(Print 540 11
								#at -1 144
							)
						)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (Btst fRemovedBra))
						(Print 540 12)
						(Print 540 13
							#at -1 144
						)
					)
					((Btst fCoconutsInBra)
						(Print 540 14)
					)
					(else
						((ScriptID 540 1) changeState: 5)
					)
				)
			)
					)			
					(3
						(if
							(or
								(== ((el value?) view?) 21) ;selected knife with wood
								(== ((el value?) view?) 22)
								(== ((el value?) view?) 34)
								(== ((el value?) view?) 2)
								
							)
							(cond
								((== ((el value?) view?) 22)
									(Print 0 28)	
								)
								((== ((el value?) view?) 34)
									(Print 0 28)	
								)
								((== ((el value?) view?) 2)
									(Print 0 23)	
								)
								((not (ego has: iKnife))
									(Print 0 22)
								)
								((or (== currentStatus egoNORMAL) (== currentStatus egoNATIVE))
							 		(theGame setScript: (ScriptID CARVING))
							 		(break)
							 	)
							 	(else
							 		(GoodIdea)
							 	)
							)
						else
;;;							(Print {You can't use those items together.}) ;English
							(Print {No puedes usar esos dos objetos juntos.}) ;Spanish
						)
					)

				

					(2 ;dull knife
							(if (== ((el value?) view?) 3) ;selected knife with wood
								(Print 0 23)
							else
;;;								(Print {You can't use those items together.}) ;English
								(Print {No puedes usar esos dos objetos juntos.}) ;Spanish
							)
					)
					(21
						(if
							(or
								(== ((el value?) view?) 3) ;selected wood with knife
								(== ((el value?) view?) 22)
								(== ((el value?) view?) 34)
							)
							(cond
								((== ((el value?) view?) 22)
									(Print 0 28)	
								)
								((== ((el value?) view?) 34)
									(Print 0 28)	
								)
								((not (ego has: iKnife))
									(Print 0 22)
								)
								((or (== currentStatus egoNORMAL) (== currentStatus egoNATIVE))
							 		(theGame setScript: (ScriptID CARVING))
							 		(break)
							 	)
							 	(else
							 		(GoodIdea)
							 	)
							)
						else
;;;							(Print {You can't use those items together.}) ;English
							(Print {No puedes usar esos dos objetos juntos.}) ;Spanish
						)
					)
					(else
;;;						(Print {You can't use those items together.}) ;English
						(Print {No puedes usar esos dos objetos juntos.}) ;Spanish
					)
				)
			)
		)
		;Dispose of everything
		(self dispose:)
	)

		
	(method (handleEvent event &tmp msg typ)
		(= msg (event message?))
		(= typ (event type?))

		(switch typ
			(keyDown
				(switch msg
					(UPARROW
						(= msg SHIFTTAB)
					)
					(DOWNARROW
						(= msg TAB)
					)
				)
			)
			(direction
				(switch msg
					(dirN
						(= msg SHIFTTAB)
						(= typ keyDown)
					)
					(dirS
						(= msg TAB)
						(= typ keyDown)
					)
				)
			)
		)

		(event
			type: typ,
			message: msg
		)

		(return (super handleEvent:event))
	)
)

