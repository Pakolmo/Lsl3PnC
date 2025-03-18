;;; Sierra Script 1.0 - (do not remove this comment)
(script# 450)
(include game.sh)
(use Main)
(use n021)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm450 0
)
(synonyms
	(blackboard blackboard blackboard blackboard blackboard special board awning)
	(buffet booth)
	(tip tip buck cup jar tips)
	(entertainer entertainer babe)
)

(local
	local0
	pattiPlayingPiano
	pianoMusic
	noEntryMsg
)
(instance rm450 of Room
	(properties
		picture 450
		south 416
		west 416
	)
	
	(method (init)
		(Load VIEW 451)
		(super init:)
		(addToPics
			add: atpPiano
			add: atpChair1
			add: atpChair2
			add: atpChair3
			add: atpChair4
			add: atpChair5
			add:
				(if (and playingAsPatti (InRoom iMarker))
					atpBlackboard2
				else
					atpBlackboard1
				)
			doit:
		)
		(self setScript: RoomScript)
		(if (and playingAsPatti (InRoom iMarker))
			(aMarker init:)
		)
	;	(DrawRect 24 45 128 157 7)
		(NormalEgo)
		(cond 
			((== currentStatus egoAUTO)
				(aPatti
					view: 800
					init:
					loop: 1
					posn: 168 107
					setCycle: Walk
				)
				(ego ignoreActors: illegalBits: 0 posn: 139 121 view: 451)
				(RoomScript changeState: 10)
				(PattiScript changeState: 6)
				(HandsOff)
			)
			((== prevRoomNum 455)
				(= pattiPlayingPiano TRUE)
				(HandsOff)
				(ego ignoreActors: illegalBits: 0 posn: 139 121 view: 451)
				(RoomScript changeState: 10)
			)
			(else
				(if (and (== showroomState SRdone) (InRoom iPenthouseKey))
					(= pattiPlayingPiano TRUE)
					(if (== (Random 0 3) 3)
						(aRoger init:)
					)
					(if (== (Random 0 3) 3)
						(aElvis init:)
					)
				)
				(if (> (ego y?) 130)
					(ego posn: 29 188)
				else
					(ego posn: 9 168)
				)
			)
		)
		(ego init:)
		(if pattiPlayingPiano
			(aPatti init:)
			(PattiScript changeState: 1)
			(blockPatti init:)
			(Btst fGotTipJar) ;Bset
			(aTips init:)
			(ego observeBlocks: blockPatti observeControl: cYELLOW)
		)
		(if (Btst fGotTipJar) (aTips dispose:))
		(if (not (Btst fGotTipJar)) (aTips init:))
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cLBLUE)
			(if (not noEntryMsg)
				(= noEntryMsg TRUE)
				(ego
					posn: (ego xLast?) (ego yLast?)
					setMotion: 0
					observeControl: cLBLUE
					forceUpd:
				)
				(if playingAsPatti
					(Print 450 0)
				else
					(Print 450 1)
				)
			)
		else
			(= noEntryMsg FALSE)
		)
		(if pattiPlayingPiano
			(ego
				observeBlocks: blockPatti
				observeControl: cYELLOW
			)
		)
	)
	
	(method (changeState newState)
		(ChangeScriptState self newState 1 1)
		(switch (= state newState)
			(0)
			(6
				;(HandsOff)
				(Ok)
				(ego
					ignoreActors:
					illegalBits: 0
				)
				(cond 
					((& (ego onControl:) cBLUE)
						(self changeState: 8)
					)
					((& (ego onControl:) cMAGENTA)
						(self changeState: 7)
					)
					(else
						(ego setMotion: MoveTo 118 (ego y?) self)
					)
				)
								(theGame setCursor: 998 (HaveMouse))
								(= theCursor 998)

			)
			(7
				(ego setMotion: MoveTo (ego x?) 113 self)
			)
			(8
				(ego setMotion: MoveTo 134 113 self)
			)
			(9
				(ego
					posn: 139 121
					view: 451
					loop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(10
				(if (!= currentStatus egoAUTO)
					(User canInput: TRUE)
					(= currentStatus egoSITTING)
				)
				(ego
					loop: (Random 1 2)
					cel: 0
					cycleSpeed: (Random 0 2)
					setCycle: EndLoop
				)
				(-- state)
				(= seconds (Random 3 6))
			)
			(11
				(Ok)
				(HandsOff)
				(= seconds (= cycles 0))
				(ego setLoop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(12
				(ego posn: 134 113)
				(NormalEgo 3)
				(= currentStatus egoNORMAL)
			)
			(13
				(HandsOff)
				(Ok)
				(= currentStatus 14)
				(if (not (Btst fMetPatti))
					(Printf 450 48 introductoryPhrase)
					(= seconds 3)
				else
					(self cue:)
				)
			)
			(14
				(if (not (Btst fMetPatti))
					(Bset fMetPatti)
					(theGame changeScore: 5)
					(Print 450 49)
				)
				(curRoom newRoom: 455)
			)
		)
	)
	
	(method (handleEvent event)
;;;		(if (or (!= (event type?) saidEvent) (event claimed?))
		(if (event claimed?)
			(return)
		)
		(cond 
			((Said 'lie')
				(cond 
					((== currentStatus egoSITTING)
						(YouAre)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					(playingAsPatti
						(Print 450 2)
					)
					((& (ego onControl:) cGREEN)
						(Print 450 3)
					)
					(
						(or
							(& (ego onControl:) cBLUE)
							(& (ego onControl:) cMAGENTA)
							(& (ego onControl:) cRED)
						)
						(self changeState: 6)
					)
					((& (ego onControl:) cBROWN)
						(Print 450 4)
					)
					(else
						(Print 450 5)
					)
				)
			)
			(
				(or
					(Said 'nightstand,(get<off),(get<up),(nightstand<up)')
					(Said 'exit/barstool,barstool')
				)
				(cond 
					((== currentStatus egoNORMAL)
						(YouAre)
					)
					((!= currentStatus egoSITTING)
						(GoodIdea)
					)
					(else
						(self changeState: 11)
					)
				)
			)
			((Said 'get/marker')
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not playingAsPatti)
						(Print 450 6)
					)
					((not (InRoom iMarker))
						(AlreadyTook)
					)
					((not (ego inRect: 10 152 55 160))
						(NotClose)
					)
					(else
						(Ok)
						(ego get: iMarker)
						(theGame changeScore: 50)
						(aMarker dispose:)
						(Print 450 7)
					)
				)
			)
			((Said 'get/tip')
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not playingAsPatti)
						(Print 450 8)
					)
					((not (Btst fGotTipJar))
						(AlreadyTook)
					)
					((not (& (ego onControl:) cBLUE))
						(Print 450 9)
					)
					(else
						(Ok)
						(ego get: iMoney)
						((Inventory at: iMoney) view: 25)
						(Format ((Inventory at: iMoney) name?) 450 10)
						(theGame changeScore: 25)
						(= dollars 43)
						(Bclr fGotTipJar)
						(aTips dispose:)
						(Print 450 11)
					)
				)
			)
			((Said 'get,buy,call/attendant,attendant,drink')
				(Print 450 12)
			)
			((Said '/attendant,attendant')
				(Print 450 13)
			)
			((or (Said 'give,backdrop/tip') (Said 'tip'))
				(cond 
					(playingAsPatti
						(Print 450 14)
					)
					((not (ego has: iMoney))
						(DontHave)
					)
					((Btst fGotTipJar)
						(Print 450 15)
					)
					(else
						(Print 450 16)
					)
				)
			)
			((Said 'gamble/keyboard')
				(cond 
					(playingAsPatti
						(Print 450 17)
					)
					((not (cast contains: aPatti))
						(Print 450 18)
						(if (== filthLevel 4)
							(Print 450 19 #at -1 144)
						)
					)
					(else
						(Print 450 20)
						(Print 450 21)
						(PattiScript changeState: 1)
					)
				)
			)
			((Said 'hear')
				(if pattiPlayingPiano
					(Print 450 22)
				else
					(Print 450 23)
				)
			)
			((Said 'look<below')
				(Print 450 24)
			)
			((Said 'look>')
				(cond 
					(
						(or
							(Said '/blackboard')
							(and (InRoom iMarker) (Said '/marker'))
						)
						(cond 
							((not playingAsPatti)
								(Print 450 25)
							)
							((not (InRoom iMarker))
								(Print 450 26)
							)
							(else
								(Print 450 27)
							)
						)
					)
					((Said '/wall,ceiling,burn')
						(Print 450 28)
					)
					((Said '/bar')
						(if pattiPlayingPiano
							(Print 450 29)
						else
							(Print 450 30)
							(Print 450 31 #at -1 144)
						)
					)
					((Said '/buffet')
						(Print 450 32)
						(Print 450 33
							#at -1 144
						)
					)
					((Said '/barstool')
						(Print 450 34)
					)
					((Said '/drink')
						(Print 450 35)
					)
					((and (Btst fGotTipJar) (Said '/tip'))
						(Print 450 36)
					)
					((Said '/door')
						(Print 450 37)
					)
					((Said '/keyboard,entertainer')
						(cond 
							((< showroomState SRdone)
								(Print 450 38)
							)
							((InRoom iPenthouseKey)
								(Print 450 39)
								(Print 450 40
									#at -1 144
								)
							)
							((not playingAsPatti)
								(Print 450 41)
							)
							((Btst fGotTipJar)
								(Print 450 42)
							)
							(else
								(Print 450 43)
							)
						)
					)
					((Said '[/bar,area]')
						(if playingAsPatti
							(Print 450 44)
						else
							(Print 450 45)
							(if pattiPlayingPiano
								(Print 450 46)
							else
								(Print 450 47)
							)
						)
					)
				)
			)
			
		
		(
		(if 
			(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emRIGHT_BUTTON))
			)
;;;					(switch theCursor ;current cursor
;;;
;;;						(999 ;just incase they have the walk cursor
;;;							(theGame setCursor: 996 (HaveMouse))
;;;							(event claimed: 1)
;;;						)
;;;						(992 
;;;							(theGame setCursor: 998 (HaveMouse))
;;;							(event claimed: 1)
;;;						)
;;;						(993 ;just incase they have the arrow cursor
;;;							(theGame setCursor: 996 (HaveMouse))
;;;							(event claimed: 1)
;;;						)
;;;						(996
;;;							(theGame setCursor: 998 (HaveMouse))
;;;							(event claimed: 1)
;;;						)
;;;						(991
;;;							(theGame setCursor: 998 (HaveMouse))
;;;							(event claimed: 1)
;;;						)
;;;						(998
;;;							(theGame setCursor: 995 (HaveMouse))
;;;							(event claimed: 1)
;;;						)
;;;						(995
;;;							(theGame setCursor: 994 (HaveMouse))
;;;							(event claimed: 1)
;;;						)
;;;						(994
;;;							(if
;;;								(or 
;;;									(== itemIcon 900)
;;;									(== itemIcon 993)
;;;								)
;;;								(theGame setCursor: 991 (HaveMouse))
;;;							else
;;;								(theGame setCursor: itemIcon (HaveMouse))
;;;								(= theCursor itemIcon)
;;;							)
;;;							(event claimed: 1)
;;;						)
;;;					)                                                                  
;;;				else ;left clicks
				
				(if ;check if clicked on Elvis
					(and
						(ClickedOnObj aElvis (event x?) (event y?))	
						(cast contains: aElvis)
					)
							(event claimed: TRUE)

					
					(switch theCursor
						(998 ;look
							(Print 450 50)
						)
						(996 ;talk
							(Print 450 52)
							(ElvisScript changeState: 3)
						)

						(else
							(event claimed: FALSE)
						)
					)

				)
								(if ;check if clicked on atpBlackboard

						(ClickedOnObj atpBlackboard1 (event x?) (event y?))	

						(event claimed: TRUE)
						(switch theCursor
							(998 ;look
							;(and (InRoom iMarker)

						(cond 
							((not playingAsPatti)
								(Print 450 25)
							)
							((not (InRoom iMarker))
								(Print 450 26)
							)
							(else
								(Print 450 27)
							)
						)
					)
							(else
								(event claimed: FALSE)
						)
					)

				)								
					
			
				(if ;check if clicked on atpChairs
					(or
						(ClickedOnObj atpChair1 (event x?) (event y?))	
						(ClickedOnObj atpChair2 (event x?) (event y?))	
						(ClickedOnObj atpChair3 (event x?) (event y?))	
						(ClickedOnObj atpChair4 (event x?) (event y?))	
						(ClickedOnObj atpChair5 (event x?) (event y?))	
					)
			
			
					(event claimed: TRUE)
					(switch theCursor
						(995 ;use sit
							(cond 
					((== currentStatus egoSITTING)
						(YouAre)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
						
						
					)
					(playingAsPatti
						(Print 450 2)
					)
					((& (ego onControl:) cGREEN)
						(Print 450 3)
					)
					(
						(or
							(& (ego onControl:) cBLUE)
							(& (ego onControl:) cMAGENTA)
							(& (ego onControl:) cRED)
						)
						
						(RoomScript changeState: 6)
							;	(theGame setCursor: 998 (HaveMouse))
						;		(= theCursor 998)
						
					)
					((& (ego onControl:) cBROWN)
						(Print 450 4)
					)
					(else
						(Print 450 5)
					)
				)
			)
								(else
							(event claimed: FALSE)
						)
					)
					)
			
			
			
			
			
					
							(if ;check if clicked on Roger
					(and
						(ClickedOnObj aRoger (event x?) (event y?))	
						(cast contains: aRoger)
					)
							(event claimed: TRUE)

					
					(switch theCursor
						(998 ;look
							(Print 450 53)
						)
						(996 ;talk
							(Print 450 54)
							(Print 450 55)
						)

						(else
							(event claimed: FALSE)
						)
					)
					)
					
				
				(if (== (event type?) evMOUSEBUTTON)
					(if (== currentStatus egoSITTING)
						(if
							(or
								(== theCursor 992)
								(== theCursor 999)
								(== theCursor 991)
								(== theCursor itemIcon) ;add
							)
							(self changeState: 11)
								(theGame setCursor: 998 (HaveMouse))
								(= theCursor 998)
						
						(if
							(and
					;			(== theCursor 991)
								(== theCursor 993)
								(== theCursor 994)
								(== theCursor 995)
								(== theCursor 996)
								(== theCursor 997)
								(== theCursor 998)
								(== theCursor 999)
							)
						)
						)
					)
				)

								(if
					(and ;ExitRoom (DrawRect 1 57 172 189 7)
						
						(> (event x?) 1) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 57) ;x2 
						(> (event y?) 172) ;y1
						(< (event y?) 189) ;y2

					)
						(event claimed: TRUE)
						(switch theCursor	
							(999 ; WALK
								(ego setMotion: MoveTo 16 192)
							)
							(else
								(event claimed: FALSE)
						)
						)
						)








								(if
					(and ;MagicMaker(DrawRect 24 45 108 137 7)
						
						(> (event x?) 24) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 45) ;x2 
						(> (event y?) 128) ;y1
						(< (event y?) 157) ;y2
						(cast contains: aMarker);  ;checks that is on the screen
					)
						(event claimed: TRUE)
						(switch theCursor	
							(998 ; LOOK
												(cond 
					(
					
							
							(and (InRoom iMarker) 
				
						(cond 
							((not playingAsPatti)
								(Print 450 25)
							)
							((not (InRoom iMarker))
								(Print 450 26)
							)
							(else
								(Print 450 27)
							)
						)
					)
								
								
								
								
								
								
								
					)))	
								
							(995 ;take	
								
												(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not playingAsPatti)
						(Print 450 6)
					)
					((not (InRoom iMarker))
						(AlreadyTook)
					)
					((not (ego inRect: 10 152 55 160))
						(NotClose)
					)
					(else
						(Ok)
						(ego get: iMarker)
						(theGame changeScore: 50)
						(aMarker dispose:)
						(Print 450 7)
					)
				)
							)(else
								(event claimed: FALSE)
						)))		
						
							(if ;check if clicked on Tips
					(and
						(ClickedOnObj aTips (event x?) (event y?))	
						(cast contains: aTips)
					)
						(event claimed: TRUE)

					
					(switch theCursor
						(998 ;Look
											(cond 
					(playingAsPatti
						(Print 450 14)
					)
					((not (ego has: iMoney))
						(DontHave)
					)
					((Btst fGotTipJar)
						(Print 450 15)
					)
					(else
						(Print 450 16)
					)
				)
						)
						(995 ;Take
											(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not playingAsPatti)
						(Print 450 8)
					)
					;((not (Btst fGotTipJar))
						
						((if(Btst fGotTipJar)
						(AlreadyTook)
					))
					((not (& (ego onControl:) cBLUE))
						(Print 450 9)
						(NotClose) ;aadd
					)
					(else
						(Ok)
						(ego get: iMoney)
						((Inventory at: iMoney) view: 25)
						(Format ((Inventory at: iMoney) name?) 450 10)
						(theGame changeScore: 25)
						(= dollars 43)
						;(Bclr fGotTipJar)
						(Bset fGotTipJar)
						(aTips dispose:)
						(Print 450 11)
					)
				)
						)(else
							(event claimed: FALSE)
							
							)	))
					
			
						
							(if ;check if clicked on Patti
					(and
						(ClickedOnObj aPatti (event x?) (event y?))	
						(cast contains: aPatti)
					)
							(event claimed: TRUE)

					
					(switch theCursor
						(998 ;look		
							(if (!= currentStatus egoSITTING)
								(Print 450 59)
							else
								(RoomScript changeState: 13)
							)
						)
						(995 ;Take
										(music fade:)
				(= pianoMusic
					(switch (Random 1 16)
						(1 110)
						(2 120)
						(3 206)
						(4 265)
						(5 323)
						(6 330)
						(7 335)
						(8 395)
						(9 399)
						(0 431)
						(11 435)
						(12 500)
						(13 560)
						(14 599)
						(15 540)
						(else  261)
					)
				)
				(Printf 450 56
					(switch pianoMusic
;;;						(110 {Sierra}) ;English
;;;						(120 {Title}) ;English
;;;						(206 {Binocular}) ;English
;;;						(265 {Tawni's}) ;English
;;;						(323 {Dewey, Cheatem and Howe}) ;English
;;;						(330 {Dale Exotic Dance}) ;English
;;;						(335 {Dale's}) ;English
;;;						(395 {Bambi's}) ;English
;;;						(399 {Fat City}) ;English
;;;						(431 {Cherri's Dance}) ;English
;;;						(435 {Cherri's}) ;English
;;;						(500 {Bamboo}) ;English
;;;						(560 {Whitewater Rafting}) ;English
;;;						(599 {Nontoonyt Jungle}) ;English
;;;						(540 {Feral Pig}) ;English
;;;						(else  {Larry Gets Crabs}) ;English
						
						(110 {Sierra}) ;Spanish
						(120 {T-tulo}) ;Spanish
						(206 {Prism*ticos}) ;Spanish
						(265 {Tawn}) ;Spanish
						(323 {Dewey, Cheatem y Howe}) ;Spanish
						(330 {Ex/tica Danza de Dale}) ;Spanish
						(335 {Dale}) ;Spanish
						(395 {Bambi}) ;Spanish
						(399 {Ciudad Obesa}) ;Spanish
						(431 {Baile de Cherri}) ;Spanish
						(435 {Cherri}) ;Spanish
						(500 {El Bambu}) ;Spanish
						(560 {Descenso en Aguas Bravas}) ;Spanish
						(599 {La Selva de Nontoonyt}) ;Spanish
						(540 {Cerdo salvaje}) ;Spanish
						(else  {Larry pilla cangrejos}) ;Spanish						
						
						
					)
				)
				(self changeState: 1)
			)
						(996 ;Talk
								(if playingAsPatti
									(Print 450 57)
								else
								(Print 450 58)
				))
						(else
							(event claimed: FALSE)
						)
					)
							)
			)
			
			
			))
	)

		)
(instance aElvis of Prop
	(properties
		y 145
		x 150
		view 453
		loop 2
	)
	
	(method (init)
		(super init:)
		(self setScript: ElvisScript)
	)
)

(instance ElvisScript of Script
	(method (changeState newState)
		(ChangeScriptState self newState 3 3)
		(switch (= state newState)
			(0
				(aElvis loop: 2 setCel: 0)
				(= cycles (Random 22 111))
			)
			(1
				(if (not (Random 0 3))
					(aElvis loop: (Random 2 3) cel: 0 setCycle: EndLoop)
				else
					(= state -1)
				)
				(= cycles (Random 22 55))
			)
			(2
				(aElvis setCycle: BegLoop self)
				(= state -1)
			)
			(3
				(aElvis loop: 4 cel: 0 setCycle: EndLoop)
				(= cycles 22)
				(= state 1)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look/man,elvis')
				(Print 450 50)
			)
			((Said 'address/elvis')
				(Print 450 51)
			)
			((Said '/elvis')
				(Print 450 52)
				(self changeState: 3)
			)
		)
	)
)

(instance aRoger of Prop
	(properties
		y 133
		x 144
		view 453
	)
	
	(method (init)
		(super init:)
		(self setScript: RogerScript)
	)
)

(instance RogerScript of Script
	(method (changeState newState)
		(ChangeScriptState self newState 4 4)
		(switch (= state newState)
			(0
				(aRoger loop: 0 setCel: 0)
				(= seconds (Random 5 10))
			)
			(1
				(if (not (Random 0 2))
					(aRoger loop: (Random 0 1) setCycle: EndLoop)
				else
					(= state -1)
				)
				(= cycles (Random 22 55))
			)
			(2
				(aRoger setCycle: BegLoop self)
				(= state -1)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look/man,hardy')
				(Print 450 53)
			)
			((Said 'address/hardy,man')
				(Print 450 51)
			)
			((Said '/hardy')
				(Print 450 54)
				(Print 450 55)
			)
		)
	)
)

(instance atpPiano of PicView
	(properties
		y 116
		x 143
		view 450
		priority 8
		signal ignrAct
	)
)

(instance aTips of View
	(properties
		y 91
		x 150
		view 450
		cel 2
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: setPri: 9 stopUpd:)
	)
)

(instance atpBlackboard1 of PicView
	(properties
		y 153
		x 36
		view 450
		cel 3
		priority 10
	)
)

(instance atpBlackboard2 of PicView
	(properties
		y 153
		x 36
		view 450
		cel 4
		priority 10
	)
)

(instance aMarker of Prop
	(properties
		y 145
		x 29
		view 450
		loop 1
		cycleSpeed 2
	)
	
	(method (init)
		(super init:)
		(self setPri: 11 setCycle: Forward)
	)
)

(instance atpChair1 of PicView
	(properties
		y 122
		x 137
		view 450
		cel 1
		priority 8
	)
)

(instance atpChair2 of PicView
	(properties
		y 133
		x 144
		view 450
		cel 1
		priority 9
	)
)

(instance atpChair3 of PicView
	(properties
		y 145
		x 150
		view 450
		cel 1
		priority 10
	)
)

(instance atpChair4 of PicView
	(properties
		y 155
		x 170
		view 450
		cel 1
		priority 11
	)
)

(instance atpChair5 of PicView
	(properties
		y 155
		x 192
		view 450
		cel 1
		priority 11
	)
)

(instance aPatti of Actor
	(properties
		y 84
		x 166
		view 452
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors:
			illegalBits: 0
			setPri: 7
			setScript: PattiScript
		)
	)
)

(instance PattiScript of Script
	(method (changeState newState &tmp pianoLoop)
		(ChangeScriptState self newState 2 2)
		(switch (= state newState)
			(0)
			(1
				(= seconds 0)
				(aPatti view: 452 viewer: pianoCycler)
				(if pianoMusic
					(= pianoLoop 1)
				else
					(= pianoMusic (Random 451 454))
					(= pianoLoop (Random 1 3))
				)
				(music stop: number: pianoMusic loop: pianoLoop play: self)
			)
			(2
				(music number: pianoMusic loop: 1 play:)
				(= pianoMusic 0)
				(= seconds (Random 3 22))
			)
			(3
				(music fade:)
				(aPatti viewer: 0 loop: 4 setCel: 0)
				(= seconds 7)
			)
			(4
				(Print 450 61)
				(self changeState: 1)
			)
			(5)
			(6
				(music stop: number: 499 loop: musicLoop play:)
				(= seconds 3)
			)
			(7
				(aPatti setMotion: MoveTo 145 112 self)
			)
			(8
				(Print 450 62)
				(Print 450 63)
				(ego get: iPenthouseKey)
				(PutInRoom iWineBottle 340)
				(theGame changeScore: 25)
				(aPatti setLoop: -1 setMotion: MoveTo 168 112 self)
			)
			(9
				(Print 450 64)
				(aPatti setMotion: MoveTo 168 46 self)
			)
			(10
				(Print 450 65 #at -1 144)
				(aPatti dispose:)
				(User canInput: TRUE)
				(= currentStatus egoSITTING)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((super handleEvent: event))
			((Said 'get,make,(ask<about),gamble/music,buy')
				(music fade:)
				(= pianoMusic
					(switch (Random 1 16)
						(1 110)
						(2 120)
						(3 206)
						(4 265)
						(5 323)
						(6 330)
						(7 335)
						(8 395)
						(9 399)
						(0 431)
						(11 435)
						(12 500)
						(13 560)
						(14 599)
						(15 540)
						(else  261)
					)
				)
				(Printf 450 56
					(switch pianoMusic
;;;						(110 {Sierra}) ;English
;;;						(120 {Title}) ;English
;;;						(206 {Binocular}) ;English
;;;						(265 {Tawni's}) ;English
;;;						(323 {Dewey, Cheatem and Howe}) ;English
;;;						(330 {Dale Exotic Dance}) ;English
;;;						(335 {Dale's}) ;English
;;;						(395 {Bambi's}) ;English
;;;						(399 {Fat City}) ;English
;;;						(431 {Cherri's Dance}) ;English
;;;						(435 {Cherri's}) ;English
;;;						(500 {Bamboo}) ;English
;;;						(560 {Whitewater Rafting}) ;English
;;;						(599 {Nontoonyt Jungle}) ;English
;;;						(540 {Feral Pig}) ;English
;;;						(else  {Larry Gets Crabs}) ;English
						
						(110 {Sierra}) ;Spanish
						(120 {T-tulo}) ;Spanish
						(206 {Prism*ticos}) ;Spanish
						(265 {Tawn}) ;Spanish
						(323 {Dewey, Cheatem y Howe}) ;Spanish
						(330 {Ex/tica Danza de Dale}) ;Spanish
						(335 {Dale}) ;Spanish
						(395 {Bambi}) ;Spanish
						(399 {Ciudad Obesa}) ;Spanish
						(431 {Baile de Cherri}) ;Spanish
						(435 {Cherri}) ;Spanish
						(500 {El Bambu}) ;Spanish
						(560 {Descenso en Aguas Bravas}) ;Spanish
						(599 {La Selva de Nontoonyt}) ;Spanish
						(540 {Cerdo salvaje}) ;Spanish
						(else  {Larry pilla cangrejos}) ;Spanish					
						
						
					)
				)
				(self changeState: 1)
			)
			((Said 'address[/entertainer]')
				(if playingAsPatti
					(Print 450 57)
				else
					(Print 450 58)
				)
			)
			((Said 'look/entertainer')
				(if (!= currentStatus egoSITTING)
					(Print 450 59)
				else
					(RoomScript changeState: 13)
				)
			)
			((Said '/entertainer')
				(Print 450 60)
			)
		)
	)
)

(instance pianoCycler of Code
	(method (doit)
		(if (not (Random 0 9))
			(aPatti cycleSpeed: (Random 0 1))
		)
		(if (not (Random 0 5))
			(aPatti loop: (Random 0 3))
		)
	)
)

(instance blockPatti of Block
	(properties
		top 90
		left 157
		bottom 111
		right 177
	)
)
