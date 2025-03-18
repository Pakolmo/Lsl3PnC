;;; Sierra Script 1.0 - (do not remove this comment)
(script# 950)
(include game.sh) (include menu.sh)
(use Main)
(use Game)
(use Gauge)
(use System)
(use Actor)
(use Intrface)
(use user)
(use Invent) ;ADD
(use Motion) ;add bambu
(use rm500) ;add bambu
(use Jump) ;add palms

(public
	PnCMenu 0
)

(local
	[txtstring 50]
	yIconStep =  4
	howLong =  50
	gShowMenu
	doMenuTimer
	menuTime
	pickedLoad ;added for load save merge
	[msgBuf 66]
	[titleBuf 22]
	local88 = [-16564 5177 19666 27846 18175 19476 14668 -11668 -14778 -2049 -12039 -6221 -28275 -28200 -29441 -24077 -12441 8987 9137 6655]
	thePic =  500
	bambooRoom
;	thirstTimer
	theEdge
	drownCycles ;rock
	temp3 ;add
	ropeState ;add
	local0
	local2
	onOtherSide
)
(instance PnCMenu of Region
	(properties)
	
	(method (init)
		(super init:)
		(self setScript: PnCMenuScript)
		(switch gLayout
			(0
				(= yPosition 20)
				(walkIcon setPri: 15 y: yPosition init:)

				(lookIcon setPri: 15 y: yPosition init:)
				(handIcon setPri: 15 y: yPosition init:)
				(talkIcon setPri: 15 y: yPosition init:)
				(smellIcon setPri: 15 y: yPosition init:)
				(invIcon setPri: 15 y: yPosition init:)
				(blockIcon setPri: 15 y: yPosition init:) ;was setPri: 14
				(levelsIcon setPri: 15 y: yPosition init:)
				(restartIcon setPri: 15 y: yPosition init:)
				;(loadIcon setPri: 15 y: yPosition init:)
				(saveIcon setPri: 15 y: yPosition init:)
				(quitIcon setPri: 15 y: yPosition init:)
				(if (!= itemIcon 900)
					(selectedItem setPri: 15 y: yPosition cel: itemIcon init:)
				else
					(selectedItem setPri: 15 y: yPosition cel: 0 init:)
				)
			)
			(2
				(= yPosition 189)
				(walkIcon setPri: 15 y: yPosition init:)

				(lookIcon setPri: 15 y: yPosition init:)
				(handIcon setPri: 15 y: yPosition init:)
				(talkIcon setPri: 15 y: yPosition init:)				
				(smellIcon setPri: 15 y: yPosition init:)

				(invIcon setPri: 15 y: yPosition init:)
				(blockIcon setPri: 15 y: yPosition init:) ;was setPri: 14
				(levelsIcon setPri: 15 y: yPosition init:)
				(restartIcon setPri: 15 y: yPosition init:)
				;(loadIcon setPri: 15 y: yPosition init:)
				(saveIcon setPri: 15 y: yPosition init:)
				(quitIcon setPri: 15 y: yPosition init:)
				(if (!= itemIcon 900)
					(selectedItem setPri: 15 y: yPosition cel: itemIcon init:)
				else
					(selectedItem setPri: 15 y: yPosition cel: 0 init:)
				)
			)
			(else 
				(walkIcon setPri: 15 y: yPosition init:)

				(lookIcon setPri: 15 y: yPosition init:)
				(handIcon setPri: 15 y: yPosition init:)
				(talkIcon setPri: 15 y: yPosition init:)
				(smellIcon setPri: 15 y: yPosition init:)

				(invIcon setPri: 15 y: yPosition init:)
				(blockIcon setPri: 15 y: yPosition init:) ;was setPri: 14
				(levelsIcon setPri: 15 y: yPosition init:)
				(restartIcon setPri: 15 y: yPosition init:)
				;(loadIcon setPri: 15 y: yPosition init:)
				(saveIcon setPri: 15 y: yPosition init:)
				(quitIcon setPri: 15 y: yPosition init:)
				(if (!= itemIcon 900)
					(selectedItem
						setPri: 15
						y: yPosition
						cel: itemIcon
						init:
						setScript: showButtons
					)
				else
					(selectedItem
						setPri: 15
						y: yPosition
						cel: 0
						init:
						setScript: showButtons
					)
				)
			)
		)
	)
)

(instance PnCMenuScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== gLayout 1)
			(switch movingButtons
				(0
					(cond 
						(
							(or
								(== yPosition 0)
								(== yPosition 400)
							)
							(if (== gShowMenu 1)
								(= yPosition 0)
								(= movingButtons 1)
							)
						)
						((>= yPosition 20)
							(cond 
								((== gShowMenu 0)
									(= movingButtons 2)
								)
								((>= menuTime howLong)
									(= doMenuTimer 0)
									(= gShowMenu 0)
									(= menuTime 0)
								)
								(else
									(++ menuTime)
								)
							)
						)
					)
				)
				(1
					(if (>= yPosition 20) (= movingButtons 0))
				)
			)
		)
		(selectedItem loop: 2 cel: itemIcon)
		(blockIcon loop: 2 cel: 0) ;inv item background cel
		(switch theCursor
			(999
				(walkIcon loop: 1)

				(lookIcon loop: 0)
				(handIcon loop: 0)
				(talkIcon loop: 0)
				(smellIcon loop: 0) ;add smell
			)
			(998
				(walkIcon loop: 0)

				(lookIcon loop: 1)
				(handIcon loop: 0)
				(talkIcon loop: 0)
				(smellIcon loop: 0) ;add smell
			)
			(995
				(walkIcon loop: 0)

				(lookIcon loop: 0)
				(handIcon loop: 1)
				(talkIcon loop: 0)
				(smellIcon loop: 0) ;add smell
			)
			(996
				(walkIcon loop: 0)

				(lookIcon loop: 0)
				(handIcon loop: 0)
				(talkIcon loop: 1)
				(smellIcon loop: 0) ;add smell
			)
			(994 ;add smell
				(walkIcon loop: 0)

				(lookIcon loop: 0)
				(handIcon loop: 0)
				(talkIcon loop: 0)
				(smellIcon loop: 1)
			)
			(itemIcon
				(walkIcon loop: 0)

				(lookIcon loop: 0 init:)
				(handIcon loop: 0)
				(talkIcon loop: 0)
				(smellIcon loop: 0) ;add smell
			)
		)
		(if (!= itemIcon 900)
			;(selectedItem view: itemIcon) ;inventory views == inventory item number
			(selectedItem view: (+ itemIcon 900)); inv # + 900
		else
			;(selectedItem cel: 0) ;inital state, no item chosen
			(selectedItem view:900) ;fix crossbar
		)
	)
	
	(method (handleEvent event &tmp haveMouse)
		(super handleEvent: event)
		(= haveMouse (HaveMouse)) ;fix rightclick crash in scummvm
		(if (== (event type?) evMOUSEBUTTON)
			(if modelessDialog ;it's better to put this up here
				(modelessDialog dispose:)
			)
			(blockIcon setCel: itemIcon) ; added to keep menu item up to date
			(if (& (event modifiers?) emRIGHT_BUTTON)
				(if (== programControl 1)
					(event claimed: 1)
				else
					(event claimed: 1)
					(= menuTime 0)
					(switch theCursor
						(itemIcon
							(theGame setCursor: 999 haveMouse)
						)
						(999
							(theGame setCursor: 998 haveMouse)
						)
						(996
							(theGame setCursor: 994 haveMouse)
						)
						(998
							(theGame setCursor: 995 haveMouse)
						)
						(995
							(theGame setCursor: 996 haveMouse)
						)
						(994 ;add smell 
							(if (or 
									(== itemIcon 900)
									(== itemIcon 993)
								)
								(theGame setCursor: 999 haveMouse)
							else
								(theGame setCursor: itemIcon haveMouse)
								(= theCursor itemIcon)
							)
						)
					)
				)
			)
			(if (not (& (event modifiers?) emRIGHT_BUTTON))
				(cond 
					((== programControl 1) (event claimed: 1))
					((< (event y?) 1)
						(event type: 1 claimed: 1)
						(switch movingButtons
							(0
								(if (== gLayout 1)
									(if (== gShowMenu 1)
										(= gShowMenu 0)
									else
										(= gShowMenu 1)
									)
								)
							)
							(1 (= movingButtons 2) (= gShowMenu 0))
							(2 (= movingButtons 1) (= gShowMenu 1))
						)
					)
					(
						(and
							(> (event x?) (walkIcon nsLeft?))
							(< (event x?) (walkIcon nsRight?))
							(> (event y?) (walkIcon nsTop?))
							(< (event y?) (walkIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
						(= menuTime 0)
						(theGame setCursor: 999 haveMouse)
					)
					(
						(and
							(> (event x?) (talkIcon nsLeft?))
							(< (event x?) (talkIcon nsRight?))
							(> (event y?) (talkIcon nsTop?))
							(< (event y?) (talkIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
						(= menuTime 0)
						(theGame setCursor: 996 haveMouse)
					)
					(
						(and
							(> (event x?) (lookIcon nsLeft?))
							(< (event x?) (lookIcon nsRight?))
							(> (event y?) (lookIcon nsTop?))
							(< (event y?) (lookIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
						(= menuTime 0)
						(theGame setCursor: 998 haveMouse)
					)
					(
						(and
							(> (event x?) (handIcon nsLeft?))
							(< (event x?) (handIcon nsRight?))
							(> (event y?) (handIcon nsTop?))
							(< (event y?) (handIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
						(= menuTime 0)
						(theGame setCursor: 995 haveMouse)
					)
					( ;add smell
						(and
							(> (event x?) (smellIcon nsLeft?))
							(< (event x?) (smellIcon nsRight?))
							(> (event y?) (smellIcon nsTop?))
							(< (event y?) (smellIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
						(= menuTime 0)
						(theGame setCursor: 994 haveMouse)
					)
					(
						(and
							(> (event x?) (invIcon nsLeft?))
							(< (event x?) (invIcon nsRight?))
							(> (event y?) (invIcon nsTop?))
							(< (event y?) (invIcon nsBottom?))
						)
						(event claimed: 1)
						(if (and (== gLayout 1) (!= movingButtons 2))
							(= movingButtons 2)
							(= gShowMenu 0)
						)
						(= menuTime 0)
						(invIcon setScript: dotheinv)
					)
					(
						(and
							(> (event x?) (selectedItem nsLeft?))
							(< (event x?) (selectedItem nsRight?))
							(> (event y?) (selectedItem nsTop?))
							(< (event y?) (selectedItem nsBottom?))
						)
						(event claimed: 1)
						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
						(= menuTime 0)
						(if (!= itemIcon 900)
							(theGame setCursor: itemIcon haveMouse)
							(= theCursor itemIcon)
						else
;;;							(Print {You must first selected an item.}) ;English
							(Print {Primero debes elegir un objeto.}) ;Spanish
						)
					)
					(
						(and
							(> (event x?) (levelsIcon nsLeft?))
							(< (event x?) (levelsIcon nsRight?))
							(> (event y?) (levelsIcon nsTop?))
							(< (event y?) (levelsIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
						(= menuTime 0)
						(invIcon setScript: dothelevels)
					)
					(
						(and
							(> (event x?) (restartIcon nsLeft?))
							(< (event x?) (restartIcon nsRight?))
							(> (event y?) (restartIcon nsTop?))
							(< (event y?) (restartIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
						(= menuTime 0)
						(restartIcon setScript: dotherestart)
					)
;;;					(
;;;						(and
;;;							(> (event x?) (loadIcon nsLeft?))
;;;							(< (event x?) (loadIcon nsRight?))
;;;							(> (event y?) (loadIcon nsTop?))
;;;							(< (event y?) (loadIcon nsBottom?))
;;;						)
;;;						(event claimed: 1)
;;;						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
;;;						(= menuTime 0)
;;;						(loadIcon setScript: dotheload)
;;;					)
					(
						(and
							(> (event x?) (saveIcon nsLeft?))
							(< (event x?) (saveIcon nsRight?))
							(> (event y?) (saveIcon nsTop?))
							(< (event y?) (saveIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
						(= menuTime 0)
						(saveIcon setScript: dothesave)
					)
					(
						(and
							(> (event x?) (quitIcon nsLeft?))
							(< (event x?) (quitIcon nsRight?))
							(> (event y?) (quitIcon nsTop?))
							(< (event y?) (quitIcon nsBottom?))
						)
						(event claimed: 1)
						(if (== movingButtons 2) (= movingButtons 1) (= gShowMenu 1))
						(= menuTime 0)
						(quitIcon setScript: dothequit)
					)
					(
						(and ;is the click on ego?
							(> (event x?) (ego nsLeft?))
							(< (event x?) (ego nsRight?))
							(> (event y?) (ego nsTop?))
							(< (event y?) (ego nsBottom?))
						)
						(switch theCursor ;what cursor?
							(29 ;Bottle wine with water on patti
								(cond 	
									((!= currentStatus egoNORMAL)
										(GoodIdea)
									)
									((not (ego has: iWineBottle))
										(DontHave)
									)
									((== ((Inventory at: iWineBottle) view?) 28)
										(Print 500 6 #icon 28 0 0)
									)
									;((if (>= curRoomNum 500) 
									((>= curRoomNum 500) ;don't use if in a condition
										(Ok)
										(theGame changeScore: 20)
										(= thirstTimer 0)
										(music number: 500 loop: musicLoop play:)
										(Print 500 7 #icon 29 0 0)
										(Print 500 8)
										(PutInRoom iWineBottle)
										(NormalEgo)
										(ego baseSetter: SteadyBase setCycle: SlowWalk)
										(= itemIcon 900) ;clear menu inv item pic
										(theGame setCursor: 998 (HaveMouse)) ;clear inv cursor, switch to look
										(Bambu changeState: 0)
										;)
									)
								)	
							)									
							(15 ;Quitar medias a patti
								(cond 
									((InRoom iPantyhose 484)
										(Print 520 3)
									)
									((InRoom iPantyhose -1)
										(DontHave)
									)
									((Btst fRemovedPantyhose)
										(Print 520 4)
									)
									((!= currentStatus egoNORMAL)
										(GoodIdea)
									)
									(else
										(BambuRock changeState: 15)
									)
								)
							)
							(11 ;make collar orchideas
								(cond
									((== ((Inventory at: iOrchids) view?) 26)
										(Print 0 30)
									)
									((!= currentStatus egoNORMAL)
										(GoodIdea)
									)
									(else
										(Ok)
										(theGame setScript: (ScriptID LEIING))
										(= itemIcon 900) ;BUG delete cursor grass
										(theGame setCursor: 999) ;Bug delete cursor grass
									)
								)
							)
							(10 ;Look degree (divorcio) on larry, there are a keycard
								(if (not (ego has: iSpaKeycard)) ;does not have keycard
									(ego get: iSpaKeycard)
									(theGame changeScore: 100)
									(Print 0 42)
								)
							)
							(9 ;add cursor #9 "fat city"
								;do clicked "fat city" on Larry
									(Ok)
									(theGame setScript: (ScriptID LOCKER))
							)
							(4 ;add cursor #4 Native grass, clicked on Larry to do a skirt.
										(cond 
											((not (ego has: iGrass))
												(Print 0 34)
											)
												((== ((Inventory at: iGrass) view?) 23)
								
															(Print 0 35)
											)
											((!= currentStatus egoNORMAL)
												(GoodIdea)
											)
											(else
												(Ok)
												(theGame setScript: (ScriptID WEAVING))
												(= itemIcon 900) ;delete cursor grass
												(theGame setCursor: 999) ;delete cursor grass
											)
										)
							)
							(999 ;walk
								(event type: 1 claimed: 0) ;Don't claim event, let walk script take it
							)
							(997 ;sierra wait
								(event type: 1 claimed: 0) ;Don't claim event, let walk script take it
							)
							(998 ;look
								(event type: 1 claimed: 1) ;claim event so other scripts don't use it
								(if playingAsPatti
									(switch (Random 0 2) ;randomly select a sentance between 0-2
									(0 (Print 950 36)) ;print string 36 in 950.txt
									(1 (Print 950 37)) ;print string 37 in 950.txt
									(2 (Print 950 38)) ;print string 38 in 950.txt
									)
								
							
								
								else
										(switch (Random 0 2) ;randomly select a sentance between 0-2
										(0 (Print 950 10)) ;print string 10 in 950.txt
										(1 (Print 950 11)) ;print string 11 in 950.txt
										(2 (Print 950 12)) ;print string 12 in 950.txt
									
										)
									)
								)

							
							(996 ;talk
								(event type: 1 claimed: 1)
								(if playingAsPatti
								(switch (Random 0 2)
									(0 (Print 950 39))
									(1 (Print 950 40))
									(2 (Print 950 41))
								)
								else
								(switch (Random 0 2)
									(0 (Print 950 13))
									(1 (Print 950 14))
									(2 (Print 950 15))
								)
								)
							)
							(995 ;hand
								(event type: 1 claimed: 1)
								(if playingAsPatti
								(switch (Random 0 2)
									(0 (Print 950 42))
									(1 (Print 950 43))
									(2 (Print 950 44))
							)
								else
								(switch (Random 0 2)
									(0 (Print 950 16))
									(1 (Print 950 17))
									(2 (Print 950 18))
								)
								)
							)
							
							(else ;inventory item
								(event type: 1 claimed: 1)
								(if playingAsPatti
								(switch (Random 0 2)
									(0 (Print 950 45))
									(1 (Print 950 46))
									(2 (Print 950 47))
								)
								else
								(switch (Random 0 2)
									(0 (Print 950 19))
									(1 (Print 950 20))
									(2 (Print 950 21))
								)
								
							)
							)
							))
							
						
					
					;Room defaults if nothing else is clicked on.
					(else 
						(switch theCursor
							(999 (event type: 1 claimed: 0))
							(998 ;look
								(event type: 1 claimed: 1)
								(switch (Random 42 44)
								(42 (Print 0 146))
								(43 (Print 0 147))
								(44 (Print 0 62))
								)
							)
							(996 ;talk 
								(event type: 1 claimed: 1)
								(Print 0 109 #at -1 144) ;"(There is no response.)"
							)
							(995 ;hand
								(event type: 1 claimed: 1)
								(Print 0 158) ;"What do you want to take?"
							)
							(997 ;wait sierra
								(event type: 1 claimed: 1)
							)
							(else ;inventory item
								(event type: 1 claimed: 1)
								(Print 0 151) ;"no need to use that here"
							)
						)
					)
				)
			)
		)
	)
	)
;ADD FROM BAMBU
(instance SteadyBase of Code
	(method (doit)
		(ego brBottom: (+ (ego y?) 1))
		(ego brTop: (- (ego brBottom?) 2))
		(ego brLeft: (- (ego x?) 10))
		(ego brRight: (+ (ego x?) 10))
	)
)

(instance SlowWalk of Forward	;was a class, but not in the table
	(properties
		cycleCnt 0
		completed 0
	)
	
	(method (doit)
		(if
			(or
				(!= (client x?) (client xLast?))
				(!= (client y?) (client yLast?))
			)
			(super doit:)
		)
	)
)

(instance dothequit of Script
	(properties)
	
	(method (changeState newState)
		(= state newState)
		(switch state
			(0
				(quitIcon loop: 1)
				(= cycles 3)
			)
			(1
				(if
					(Print
						950 26
;;;						#title {Quit} ;English
						#title {Salir} ;Spanish
						#font 1
;;;						#button { Quit_} 1 ;English
;;;						#button { Oops } 0 ;English
						#button { Salir_} 1 ;Spanish
						#button { Ups } 0 ;Spanish
					)
					(= quit 1)
				else
					(quitIcon loop: 0)
				)
			)
		)
	)
)

(instance dothesave of Script
	(properties)
	
	(method (changeState newState &tmp loadOrSave)
		(= state newState)
		(switch state
			(0
				(saveIcon loop: 1) ;change to icon "clicked"
				(= cycles 3) ;wait 3 cycles, then goto next state.
			)
			(1
				(= loadOrSave 
					(Print
						950 30 
;;;						#button {\n__SAVE__\n_} 0 ;#button (string) (pickedLoad==1) ;ENGLISH
;;;						#button {\n__LOAD__\n_} 0 ;(pickedLoad=0) ;ENGLISH
						#button {\n__SALVAR__\n_} 0 ;#button (string) (pickedLoad==1) ;SPANISH
						#button {\n__CARGAR__\n_} 1 ;(pickedLoad=0) ;SPANISH
					)
				)
				(switch loadOrSave
					(1
						(theGame restore:)
						(= cycles 1)
					)
					(else
						(if (Btst 3)
							(Print 997 8 ;(Print textNumber stringNumber)
;;;								#title {Not now, I have a headache!} ;can't save right now ;ENGLISH
								#title {Ahora no, ^me duele la cabeza!} ;can't save right now ;SPANISH
							)
						else
						;	(theGame save:) ;open the save game window
							(Bset 0) ;use un-use flag 0 for save hack
							(= autoSaveTimer 0) ;reset autosave reminder
							(= secondsBetweenReminders 0)
						)
						(= cycles 1)
					)
				)
			)
			(2
				(saveIcon loop: 0)
			)
		)
	)
)

(instance dotherestart of Script
	(properties)
	
	(method (changeState newState)
		(= state newState)
		(switch state
			(0
				(restartIcon loop: 1)
				(= cycles 3)
			)
			(1
				(if
					(Print MENU 9
;;;	 					#title {Restart} ;ENGLISH
	 					#title {Reiniciar} ;SPANISH
						#icon 57 0 playingAsPatti
						#font bigFont
;;;						#button {Restart} 1 ;ENGLISH
;;;						#button { Oops_} 0 ;ENGLISH
						#button {Reiniciar} 1 ;SPANISH
						#button { Ups_} 0 ;Spanish			
					)
					(theGame restart:)
				else
					(restartIcon loop: 0)
					(= newState 0)
				)
			)
		)
	)
)

(instance dothelevels of Script
	(properties)
	
	(method (changeState newState &tmp [str 220] newTextColor newBackColor)
		(= state newState)
		(switch state
			(0
				(levelsIcon loop: 1)
				(= cycles 3)
			)
			(1
				(= sGauge
					(PrintSpecial
						950 33 ;"configuration:" 
;;;						#button {\n_SPEED_\n_} 1 ;#button (string) (pickedLoad==1) ;ENGLISH
;;;						#button {\n_VOLUME_\n_} 0 ;(pickedLoad=0) ;ENGLISH
						#button {\n_VELOCIDAD_\n_} 1 ;#button (string) (pickedLoad==1) ;SPANISH
						#button {\n_VOLUMEN_\n_} 2 ;(pickedLoad=0) ;SPANISH
;;;						#button {\n_INSULT_\n_} 3 ;ENGLISH
;;;						#button {\n_AUTOSAVE_\n_} 4 ;ENGLISH
;;;						#button {\n_ABOUT_\n_} 5 ;ENGLISH
;;;						#button {\n_TEXT COLOR_\n_} 6 ;ENGLISH
						#button {\n_INSULTO_\n_} 3 ;SPANISH
						#button {\n_AUTOSALVAR_\n_} 4 ;SPANISH
						#button {\n__CREDITOS_\n_} 5 ;SPANISH
						#button {\n_COLORES_\n_} 6 ;SPANISH

					)
				)
				(switch sGauge
					(1
						(= state 9)	
					)
					(2
						(= state 19)
					)
					(3
						(= state 29)
					)
					(4
						(= state 39)
					)
					(5
						(= state 49)
					)
					(6
						(= state 59)
					)

				)
				(= cycles 1)
			)
			(10
				(if (HaveMem GaugeSize)
					(= pncSpeed
						((Gauge new:)
							description:
								(Format @str 950 34)
;;;							text: {Animation Speed} ;ENGLISH
							text: {Velocidad del Juego} ;SPANISH
							normal: 10
;;;							higher: {Faster} ;ENGLISH
;;;							lower: {Slower} ;ENGLISH
							higher: {Subir} ;SPANISH
							lower: {Bajar} ;SPANISH
							doit: (- 16 speed)
						)
					)
					(theGame setSpeed: (- 16 pncSpeed))
					(DisposeScript GAUGE)
				)
				(= state 99)
				(= cycles 1)
			)
			(20
				
				(if (HaveMem GaugeSize)
					(= pncVolume
						((Gauge new:)
							description:
								(Format @str 950 35)
;;;							text: {Sound Volume} ;ENGLISH
							text: {Volumen} ;SPANISH
							normal: 15
;;;							higher: {Louder} ;ENGLISH
;;;							lower: {Softer} ;ENGLISH
							higher: {Subir} ;SPANISH
							lower: {Bajar} ;SPANISH
							doit: (DoSound ChangeVolume pncVolume)
						)
					)
					(DoSound ChangeVolume pncVolume)
					(DisposeScript GAUGE)
					(= state 99)
					(= cycles 1)
				)	
			)
			(30 ;insult
;;;				(GetInput (Format @str expletive) 38 {Enter your favorite expletive:} ;English
				(GetInput (Format @str expletive) 38 {Introduce tu insulto preferido:} ;Spanish
				)
				(if (> (StrLen @str) 4) (Format expletive @str))
			)
			(40
				(if
					(>
						0
						(= minutesBetweenReminders
;;;							(GetNumber {Minutes Between Reminders:} minutesBetweenReminders) ;English
							(GetNumber {Minutos entre recordatorios:} minutesBetweenReminders) ;Spanish
						)
					)
					(= minutesBetweenReminders 0)
				)
			)
			(50 ;About/sobre
				(Print MENU 14 ;MENU 0
					#font smallFont
					#mode teJustCenter
;;;					#title {An Al Lowe Production} ;english
					#title {Una Producci>n de Al Lowe} ;spanish
					#icon 51 0 0
				)
				(Print
					(Format @str MENU 15 version) ;MENU 1 version
					#font smallFont
					#mode teJustCenter
					#title {The Cast of Thousands} ;english
					#title {El elenco de los miles} ;spanish
					#at -1 30
					#width 234
				)
				(Format @filthStr MENU 2
					(switch filthLevel
;;;						(4 {Totally Raunchiest}) ;english
;;;						(3 {Really Filthy}) ;english
;;;						(2 {Pretty Dirty}) ;english
;;;						(1 {Rather Risque}) ;english
;;;						(else  {Mother Goose}) ;english
						
						(4 {Totalmente Obscena}) ;spanish
						(3 {M*s guarrilla }) ;spanish
						(2 {Bastante Sucia}) ;spanish
						(1 {M|nimo Riesgo}) ;spanish
						(else  {Para Criajos}) ;spanish						
					)
				)
				(Print
					(cond 
						(gameHours
							(Format @str MENU 3
								@filthStr
								gameHours (if (== gameHours 1) {} else {s})
								gameMinutes (if (== gameMinutes 1) {} else {s})
								gameSeconds (if (== gameSeconds 1) {} else {s})
								score (if (== score 1) {} else {s})
							)
						)
						(score
							(Format @str MENU 4
								@filthStr
								gameMinutes (if (== gameMinutes 1) {} else {s})
								gameSeconds (if (== gameSeconds 1) {} else {s})
								score (if (== score 1) {} else {s})
							)
						)
						(else
							(Format @str MENU 5
								@filthStr
								gameMinutes (if (== gameMinutes 1) {} else {s})
								gameSeconds (if (== gameSeconds 1) {} else {s})
							)
						)
					)
					#font smallFont
					#mode teJustCenter
;;;					#title {Get a Life!} ;english
					#title {^B{scate una vida!} ;spa
				)
			)
			(60 ;text color
				(= newTextColor 16)
				(while (and (u> newTextColor 15) (!= newTextColor -1))
;;;					(= newTextColor (GetNumber {New Text Color: (0-15)})) ;English
					(= newTextColor (GetNumber {Nuevo Color de Texto: (0-15)})) ;Spanish
				)
				(if (!= newTextColor -1)
					(= newBackColor 16)
					(while
						(and
							(!= newBackColor -1)
							(or (u> newBackColor vWHITE) (== newBackColor newTextColor))
						)
;;;						(= newBackColor (GetNumber {New Background Color: (0-15)})) ;English
						(= newBackColor (GetNumber {Nuevo Color de Fondo: (0-15)})) ;Spanish
					)
					(if (!= newBackColor -1)
						(= myTextColor newTextColor)
						(= myBackColor newBackColor)
					)
				)
				(systemWindow color: myTextColor back: myBackColor)	
			)

			
			
			(100 (levelsIcon loop: 0))
		)
	)
)

(instance dotheinv of Script
	(properties)
	
	(method (changeState newState)
		(= state newState)
		(switch state
			(0
				(if (== canTab 1)
					(= gPreviousCursor theCursor)
					(invIcon loop: 1)
					(= cycles 3)
				else
					(Print 950 9)
				)
			)
			(1 (= doInventory 1)
				(if (HaveMem 1024) ;InvSize)
					(inventory showSelf: ego)
				)
			)
		)
	)
)

(instance walkIcon of Prop
	(properties
		y 189
		x 14
		view 950
		loop 0
		cel 0
	)
)

(instance talkIcon of Prop
	(properties
		y 189
		x 98
		view 950
		cel 3
		loop 0
	)
)

(instance lookIcon of Prop
	(properties
		y 189
		x 42
		view 950
		cel 1
		loop 0
	)
)

(instance handIcon of Prop
		(properties
		y 189
		x 70
		view 950
		cel 2
		loop 0
	)




)

(instance smellIcon of Prop
	(properties
		y 189
		x 126 ;move the rest of the icons down (x + 28)
		view 950
		cel 4
		loop 0
	)
)

(instance invIcon of Prop
	(properties
		y 189
		x 152 ;move the rest of the icons down (x + 28)
		view 950
		cel 5
		loop 0
	)
)

(instance blockIcon of Prop
	(properties
		y 189
		x 186 ;228 ;188
		view 950
		loop 2
		cel 0
	)
)

(instance levelsIcon of Prop
	(properties
		y 189
		x 220
		view 950
		cel 6
		loop 0
	)
)

(instance restartIcon of Prop
	(properties
		y 189
		x 248
		view 950
		cel 7
		loop 0
	)
)

;;;(instance loadIcon of Prop ;combine load/save icons to make room for smell
;;;	(properties
;;;		y 189
;;;		x 250
;;;		view 950
;;;		cel 7
;;;		loop 0
;;;	)
;;;)

(instance saveIcon of Prop
	(properties
		y 189
		x 276
		view 950
		cel 8
		loop 0
	)
)

(instance quitIcon of Prop
	(properties
		y 189
		x 304
		view 950
		cel 9
		loop 0
	)
)

(instance selectedItem of Prop
	(properties
		y 189
		x 186 ;
		view 0 ;950
		loop 0; ;3
		cel 0
	)
)

(instance showButtons of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(switch movingButtons
			(0)
			(1
				(if (< yPosition 20)
					(= yPosition (+ yPosition yIconStep))
					(= menuTime 0)
				else
					(= movingButtons 0)
					(= doMenuTimer 1)
				)
			)
			(2
				(if (and (> yPosition 0) (< yPosition 400))
					(= yPosition (- yPosition yIconStep))
				else
					(= movingButtons 0)
					(= yPosition 400)
				)
			)
		)
		(walkIcon y: yPosition)
		(lookIcon y: yPosition)
		(handIcon y: yPosition)
		(talkIcon y: yPosition)
		(smellIcon y: yPosition)
		(invIcon y: yPosition)
		(blockIcon y: yPosition)
		(levelsIcon y: yPosition)
		(restartIcon y: yPosition)
		;(loadIcon y: yPosition)
		(saveIcon y: yPosition)
		(quitIcon y: yPosition)
		(if (!= itemIcon 900)
			(selectedItem y: yPosition)
		else
			(selectedItem y: yPosition)
		)
	)
)
(instance BambuRock of Script
	(method (doit)
		(super doit:)
		(cond 
			((and (& (ego onControl: origin) cLBLUE) (== currentStatus egoNORMAL))
				(self changeState: 1)
			)
			((and (== currentStatus egoDROWNING) (< 8 (++ drownCycles)))
				(= drownCycles 0)
				(ego
					setLoop: (+ (Random 0 1) (* 2 (< (ego y?) 107)))
				)
			)
			((and (& (ego onControl:) cBLUE) (== currentStatus egoNORMAL))
				(self changeState: 12)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(if (!= currentStatus egoDROWNING)
					(= currentStatus egoDROWNING)
					(soundFX stop:)
					(music number: 6 loop: -1 play:)
				)
				(HandsOff)
				(ego
					view: 812
					setLoop: 0
					setStep: 1 3
					setCycle: Forward
					setPri: 8
					illegalBits: 0
				)
				(if (> (ego y?) 137)
					(ego posn: 58 (ego y?) setMotion: MoveTo 58 137 self)
				else
					(ego posn: (- (ego x?) 20) (ego y?))
					(self cue:)
				)
			)
			(2
				(if (> (ego y?) 100)
					(ego setMotion: MoveTo 86 100 self)
				else
					(self cue:)
				)
			)
			(3
				(ego setMotion: MoveTo 105 83 self)
			)
			(4
				(= currentStatus egoDYING)
				(soundFX stop:)
				(music number: 4 loop: 1 play:)
				(ego setPri: 2 setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(5
				(ego setStep: 1 8 setMotion: MoveTo (ego x?) 180 self)
			)
			(6
				(cls)
				(if debugging
					(NormalEgo)
					(= currentStatus egoNORMAL)
					(ego posn: 178 100)
				else
					(theGame setScript: (ScriptID DYING))
					((ScriptID DYING)
						caller: 522
						register: (Format @msgBuf 520 30)
						next: (Format @titleBuf 520 31)
					)
				)
			)
			(7
				(HandsOff)
				(= currentStatus egoDRINKWATER)
				(ego
					view: 511
					cycleSpeed: 2
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(8
				(ego setLoop: 1 cel: 0 setCycle: Forward)
				(= cycles
					(* 2 (ego cycleSpeed?) 4 (- (NumCels ego) 1))
				)
			)
			(9
				(ego setLoop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(10
				(= seconds 3)
			)
			(11
				(theGame changeScore: 42)
				(Bset fDrankRiverWater)
				(NormalEgo loopW)
				(= currentStatus egoNORMAL)
				(Print 520 32)
			)
			(12
				(HandsOff)
				(Print
					(Format @msgBuf 520 33 expletive)
					#at -1 10
					#dispose
				)
				(= currentStatus egoFALLING)
				(soundFX stop:)
				(music number: 4 loop: 1 play:)
				(ego
					view: 813
					setLoop: 0
					cel: 0
					illegalBits: 0
					ignoreActors:
					setPri: 2
					setCycle: EndLoop self
				)
			)
			(13
				(ego setStep: 1 8 setMotion: MoveTo (ego x?) 188 self)
				(if debugging
					(= state 5)
				)
			)
			(14
				(curRoom newRoom: 525)
			)
			(15
				(HandsOff)
				(Ok)
				(theGame changeScore: 15)
				(Bset fRemovedPantyhose)
				(ego
					view: 521
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(16
				(ego cel: 3 setCycle: BegLoop self)
			)
			(17
				(NormalEgo)
			)
			(18
				(HandsOff)
				(Ok)
				(theGame changeScore: 40)
				(= currentStatus egoREMOVEPANTYHOSE)
				(ego illegalBits: 0 setMotion: MoveTo 213 104 self)
			)
			(19
				(ego
					view: 521
					loop: 1
					cel: 0
					cycleSpeed: 1
					setPri: 10
					setCycle: EndLoop self
				)
			)
			(20
				(= cycles 11)
			)
			(21
				(ego loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(22
				(= cycles 11)
			)
			(23
				(Print 520 34 #icon 15 0 0 #at -1 10)
				(ego setLoop: 3 cel: 0 posn: 212 71 setCycle: EndLoop self)
			)
			(24
				(ego setPri: 2)
				(= cycles 5)
			)
			(25
				(ego
					setStep: 1 1
					setMotion: MoveTo (ego x?) (+ 30 (ego y?)) self
				)
			)
			(26
				(Print 520 35 #at -1 10)
				(curRoom newRoom: 525)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((or (Said 'get/drink,water') (Said 'drink') (Said 'drink/water'))
				(cond 
					((Btst fDrankRiverWater)
						(Print 520 0)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (& (ego onControl:) cLBLUE))
						(NotClose)
					)
					(else
						(self changeState: 7)
					)
				)
			)
			((Said 'use,attach/bra')
				(cond 
					((not (ego has: iBra))
						(DontHave)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					(else
						(Print 520 1)
					)
				)
			)
			((Said 'use/hose')
				(if (not (ego has: iPantyhose))
					(DontHave)
				else
					(Print 520 2)
				)
			)
			((Said 'drain,(off<get),(get<off)/hose')
				(cond 
					((InRoom iPantyhose 484)
						(Print 520 3)
					)
					((InRoom iPantyhose -1)
						(DontHave)
					)
					((Btst fRemovedPantyhose)
						(Print 520 4)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					(else
						(self changeState: 15)
					)
				)
			)
			((Said '(backdrop<on),wear/hose')
				(cond 
					((InRoom iPantyhose 484)
						(Print 520 3)
					)
					((InRoom iPantyhose -1)
						(DontHave)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (Btst fRemovedPantyhose))
						(Print 520 5)
					)
					(else
						(Print 520 6)
						(theGame changeScore: -15)
						(Bclr fRemovedPantyhose)
					)
				)
			)
			((Said 'attach/hose>')
				(cond 
					((not (ego has: iPantyhose))
						(Print 520 7)
					)
					((not (Btst fRemovedPantyhose))
						(Print 520 8)
					)
					((Said '//noword')
						(Print 520 9)
					)
					((not (Said '//boulder'))
						(Print 520 10)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (& (ego onControl:) cRED))
						(Print 520 11)
					)
					(else
						(self changeState: 18)
					)
				)
				(event claimed: TRUE)
			)
			((Said 'look>')
				(cond 
					((Said '/palm')
						(Print 520 12)
					)
					((Said '/creek')
						(Print 520 13)
					)
					((Said '/boulder,boob')
						(if (& (ego onControl:) cRED)
							(Print 520 14)
						else
							(Print 520 15)
						)
					)
					((Said '/ledge,cliff,canyon')
						(Print 520 16)
					)
					((Said '/cascade,cascade,water')
						(Print 520 17)
						(Print 520 18 #at -1 144)
					)
					((Said '[/area]')
						(Print 520 19)
					)
				)
			)
			((Said '(up<climb),climb/boulder,arch')
				(Print 520 20)
			)
			((or (Said '(climb,go)<(down,above)') (Said 'decrease/i'))
				(Print 520 21)
			)
			((Said 'climb')
				(Print 520 22)
				(Print 520 23 #at -1 144)
			)
			((Said 'drag,grasp,get/bush,hemp')
				(Print 520 24)
			)
			((Said 'get,use/palm')
				(Print 520 25)
			)
			((Said '/bush')
				(Print 520 26)
			)
			((Said '/arch')
				(Print 520 27)
			)
			((Said '/flower')
				(Print 520 28)
			)
			((Said 'jump')
				(Print 520 29)
			)
		)
	)
)




(instance Bambu of Script
	(method (doit)
		(super doit:)
		(if (ego edgeHit?)
			(= theEdge (ego edgeHit?))
			(ego edgeHit: 0 illegalBits: 0)
			(theGame setCursor: waitCursor TRUE)
			(HandsOff)
			(++ thirstTimer)
			(self changeState: 0)
			(cond 
				((< thirstTimer 8)
					(ego view: 800 moveSpeed: 0)
				)
				((< thirstTimer 14)
					(ego view: 501 moveSpeed: 0)
					(if (!= 501 (music number?))
						(music fade:)
					)
				)
				((< thirstTimer 17)
					(ego view: 502 moveSpeed: 1)
					(if (!= 502 (music number?))
						(music fade:)
					)
				)
				((< thirstTimer 18)
					(ego view: 503 moveSpeed: 2)
					(if (!= 503 (music number?))
						(music fade:)
					)
				)
				(else
					(ego view: 503 moveSpeed: 3)
					(self changeState: 2)
				)
			)
			(switch theEdge
				(NORTH
					(if (== bambooRoom 1)
						(music fade:)
						(if (not (Btst fPassedMaze))
							(theGame changeScore: 100)
							(Print 500 0)
							(Print 500 1)
						)
						(curRoom newRoom: 510)
						(return)
					else
						(-= bambooRoom 8)
					)
				)
				(SOUTH
					(if (== bambooRoom 68)
						(curRoom newRoom: 245)
						(return)
					else
						(+= bambooRoom 8)
					)
				)
				(EAST
					(++ bambooRoom)
				)
				(WEST
					(-- bambooRoom)
				)
			)
			(if (== thePic 505)
				(= thePic 500)
				(switch theEdge
					(NORTH
						(ego posn: (Random 130 234) 187)
					)
					(SOUTH
						(ego posn: 177 26)
					)
					(EAST
						(ego posn: 1 74)
					)
					(else
						(ego posn: 317 74)
					)
				)
			else
				(= thePic 505)
				(switch theEdge
					(NORTH
						(ego posn: (Random 80 163) 187)
					)
					(SOUTH
						(ego posn: 188 26)
					)
					(EAST
						(ego posn: 1 83)
					)
					(else
						(ego posn: 314 128)
					)
				)
			)
			(proc500_1)
			(Animate (cast elements?) FALSE)
			(ego illegalBits: cWHITE)
			(HandsOn)
			(theGame setCursor: normalCursor (HaveMouse))
			(return)
		)
		(if (== (GameIsRestarting) 2)
			(proc500_1)
			(Animate (cast elements?) FALSE)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 4)
			)
			(1
				(cond 
					(
						(and
							(>= thirstTimer 8)
							(<= thirstTimer 13)
							(!= 501 (music number?))
						)
						(music number: 501 loop: musicLoop play:)
					)
					(
						(and
							(>= thirstTimer 14)
							(<= thirstTimer 16)
							(!= 502 (music number?))
						)
						(music number: 502 loop: musicLoop play:)
					)
					(
						(and
							(<= thirstTimer 18)
							(>= thirstTimer 17)
							(!= 503 (music number?))
						)
						(music number: 503 loop: musicLoop play:)
					)
				)
				(cond 
					((== thirstTimer 4)
						(Print 500 13)
					)
					((== thirstTimer 8)
						(Print 500 14)
					)
					((== thirstTimer 12)
						(Print 500 15)
					)
					((== thirstTimer 16)
						(Print 500 16)
						(Print 500 17)
						(Print 500 18)
						(Print 500 19)
					)
				)
			)
			(2
				(= seconds 3)
			)
			(3
				(Print 500 20)
				(= seconds 3)
			)
			(4
				(Print 500 21)
				(= seconds 3)
			)
			(5
				(HandsOff)
				(Print 500 22)
				(ego
					illegalBits: 0
					setMotion: 0
					view: 504
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(6
				(theGame setScript: (ScriptID DYING))
				((ScriptID DYING)
					caller: 505
					register: (Format @msgBuf 500 23)
					next: (Format @titleBuf 500 24)
				)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?)
	;	(if (or (!= (event type?) saidEvent) (event claimed?))
			(return (event claimed?))

		)
		(return
			(cond 
				((Said 'get/bamboo')
					(Print 500 2)
				)
				((Said 'climb/bamboo')
					(Print 500 3)
				)
				((Said 'attack/bamboo')
					(Print 500 4)
				)
				((Said 'nightstand,(get,nightstand<up)')
					(Print 500 5)
				)
				(
					(or
						(Said 'sip/water')
						(Said 'get/drink<1')
						(Said 'use,drink,drain/water,beer,bottle')
					)
					(cond 
						((!= currentStatus egoNORMAL)
							(GoodIdea)
						)
						((not (ego has: iWineBottle))
							(DontHave)
						)
						((== ((Inventory at: iWineBottle) view?) 28)
							(Print 500 6 #icon 28 0 0)
						)
						(else
							(Ok)
							(theGame changeScore: 20)
							(= thirstTimer 0)
							(music number: 500 loop: musicLoop play:)
							(Print 500 7 #icon 29 0 0)
							(Print 500 8)
							(PutInRoom iWineBottle)
							(NormalEgo)
							(ego baseSetter: SteadyBase setCycle: SlowWalk)
							(self changeState: 0)
						)
					)
				)
				((Said 'look>')
					(cond 
						((Said '[/area]')
							(Print 500 9)
							(Print 500 10 #at -1 144)
						)
						((Said '/bamboo')
							(Print 500 11)
							(Print (Format @msgBuf 500 12 bambooStalksSeen) #at -1 144)
							(++ bambooStalksSeen)
						)
					)

			)
			)
			)
			(cond
						(
				(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emRIGHT_BUTTON))
			)
;;;			(if
;;;				(and ;exit room
;;;						(> (event x?) 307) ;x1 (> (mouseX) (left edge of rectangle))
;;;						(< (event x?) 319) ;x2 
;;;						(> (event y?) 24) ;y1
;;;						(< (event y?) 85) ;y2
;;;					)
;;;					(event claimed: TRUE)
;;;					(switch theCursor					
;;;						(999 ; WALK
;;;							(ego setMotion: MoveTo 321 58)
;;;						)
;;;						(else
;;;							(event claimed: FALSE)
;;;						)
;;;					)
;;;			)
			
			
					)
			)		
			
			
		)
	)
;;;(instance RmPalmeras of Script
;;;	(method (doit)
;;;		(cond 
;;;			(
;;;				(and
;;;					(== (ego view?) 534)
;;;					(== (ego loop?) 1)
;;;					(== (ego cel?) 1)
;;;				)
;;;				(soundFX number: 530 loop: 1 play:)
;;;			)
;;;			(
;;;			(and (& (ego onControl:) cBLUE) (== currentStatus egoNORMAL))
;;;				(= currentStatus egoFALLING)
;;;				(ego posn: (- (ego x?) 25) (ego y?))
;;;				(self changeState: 65)
;;;			)
;;;			(
;;;			(and (& (ego onControl:) cLGREY) (== currentStatus egoNORMAL))
;;;				(= currentStatus egoFALLING)
;;;				(ego posn: (+ (ego x?) 25) (ego y?))
;;;				(self changeState: 65)
;;;			)
;;;			(
;;;			(and (& (ego onControl:) cBROWN) (== currentStatus egoNORMAL))
;;;				(= currentStatus egoFALLING)
;;;				(ego posn: (ego x?) (+ (ego y?) 5))
;;;				(self changeState: 65)
;;;			)
;;;			((& (ego onControl:) cLGREEN)
;;;				(curRoom newRoom: 540)
;;;			)
;;;			(
;;;				(and
;;;					(== ropeState 3)
;;;					(or (!= 142 (ego x?)) (!= 128 (ego y?)))
;;;				)
;;;				(= ropeState 531)
;;;				(self changeState: 58)
;;;			)
;;;			((and (== currentStatus egoNORMAL) (not (Btst fDrankRiverWater)) (not onOtherSide))
;;;				(cond 
;;;					((== roomSeconds 30)
;;;						(++ roomSeconds)
;;;						(Print 530 0)
;;;					)
;;;					((== roomSeconds 60)
;;;						(++ roomSeconds)
;;;						(Print 530 1)
;;;					)
;;;					((or (> roomSeconds 90) (== ropeState 4))
;;;						(self changeState: 12)
;;;					)
;;;				)
;;;			)
;;;		)
;;;		(super doit:)
;;;	)
;;;	
;;;	(method (changeState newState)
;;;		(switch (= state newState)
;;;			(0 (HandsOff))
;;;			(1
;;;				(ego
;;;					ignoreActors:
;;;					setStep: 1 1
;;;					setMotion: JumpTo 170 155 self
;;;				)
;;;			)
;;;			(2
;;;				(ego setLoop: 1 setCel: 0)
;;;				(music number: 12 loop: 1 play:)
;;;				(ShakeScreen 3 shakeSDown)
;;;				(= seconds 4)
;;;			)
;;;			(3
;;;				(Print 530 64)
;;;				(= seconds 4)
;;;			)
;;;			(4
;;;				(Print 530 65)
;;;				(= seconds 4)
;;;			)
;;;			(5
;;;				(ego setCycle: EndLoop self)
;;;				(addToPics add: atpTits doit:)
;;;			)
;;;			(6
;;;				(music number: 599 loop: musicLoop play:)
;;;				(ego posn: 170 155 cel: 1)
;;;				(NormalEgo 2)
;;;				(= currentStatus egoNORMAL)
;;;				(theGame setSpeed: saveSpeed)
;;;			)
;;;			(7
;;;				(if (not (Btst fGotPot))
;;;					(Bset fGotPot)
;;;					(theGame changeScore: 10)
;;;				)
;;;				(Ok)
;;;				(Print 530 66)
;;;				(HandsOff)
;;;				(= local2 0)
;;;				(ego
;;;					view: 531
;;;					cycleSpeed: 1
;;;					setStep: 2 1
;;;					setLoop: 0
;;;					cel: 0
;;;					setCycle: EndLoop self
;;;				)
;;;			)
;;;			(8
;;;				(ego
;;;					cycleSpeed: 0
;;;					setLoop: 1
;;;					setCycle: Forward
;;;					setMotion: MoveTo (Random 111 195) (ego y?) self
;;;				)
;;;			)
;;;			(9
;;;				(= cycles 11)
;;;				(if (> 4 (++ local2))
;;;					(= state 7)
;;;				)
;;;			)
;;;			(10
;;;				(ego
;;;					cycleSpeed: 1
;;;					setLoop: 0
;;;					setCel: 255
;;;					setCycle: BegLoop self
;;;				)
;;;			)
;;;			(11
;;;				(NormalEgo)
;;;				(= ropeState 1)
;;;				(ego loop: 2 get: iMarijuana)
;;;				(Print 530 67)
;;;			)
;;;			(12
;;;				(HandsOff)
;;;				(= currentStatus egoDYING)
;;;				(Print 530 68)
;;;				(ego loop: 2)
;;;				(music fade:)
;;;				(= seconds 3)
;;;			)
;;;			(13
;;;				(ego view: 804 loop: 1 setCel: 255 setCycle: BegLoop self)
;;;			)
;;;			(14
;;;				(theGame setScript: (ScriptID DYING))
;;;				((ScriptID DYING)
;;;					caller: 537
;;;					register: (Format @msgBuf 530 69)
;;;					next: (Format @titleBuf 530 70)
;;;				)
;;;			)
;;;			(15
;;;				(Ok)
;;;				(HandsOff)
;;;				(ego setMotion: MoveTo 151 142 self)
;;;				(music fade:)
;;;			)
;;;			(16
;;;				(Print 530 71 #at -1 10 #icon 20 0 0)
;;;				(ego view: 531 loop: 8 cel: 0 setCycle: EndLoop self)
;;;				(= seconds 3)
;;;			)
;;;			(17
;;;				;(theGame changeScore: -50)
;;;				(if (== currentStatus 535)
;;;					(Print 530 71 #at -1 10 #icon 20 0 0 #dispose)
;;;					;(= state 24)
;;;					(self changeState: 25)
;;;				else
;;;					(Print 530 72 #at -1 10 #icon 20 0 0 #dispose)
;;;				)
;;;				(= seconds 3)
;;;			)
;;;			(18
;;;				(ego loop: 2 cel: 0 setCycle: EndLoop self)	
;;;			)
;;;			(19
;;;				(Print 530 73 #dispose #at -1 10)
;;;				(= local2 0)
;;;				(ego loop: 3 cel: 0 setCycle: EndLoop self)
;;;			)
;;;			(20
;;;				(ego loop: 4 cel: 0 setCycle: EndLoop self)
;;;			)
;;;			(21
;;;				(if (> 3 (++ local2)) (= state 19))
;;;				(= cycles 20)
;;;			)
;;;			(22
;;;				(ego loop: 5 cel: 0 setCycle: Forward)
;;;				(= cycles (* 3 (NumCels ego)))
;;;			)
;;;			(23
;;;				(ego loop: 6 cel: 0 setCycle: EndLoop self)
;;;			)
;;;			(24
;;;				(ego loop: 7 cel: 0 setCycle: EndLoop)
;;;				(= cycles 44)
;;;			)
;;;			(25
;;;				(Print 530 74 #dispose #at -1 10)
;;;				(music number: 531 loop: -1 play:)
;;;				(= seconds 3)
;;;			)
;;;			(26
;;;				(theGame setSpeed: 6)
;;;				(ego
;;;					view: 533
;;;					posn: (ego x?) (- (ego y?) 26)
;;;					cycleSpeed: 1
;;;					loop: 0
;;;					cel: 0
;;;					setCycle: EndLoop self
;;;				)
;;;			)
;;;			(27
;;;				(ego
;;;					put: iMarijuana -1
;;;					setLoop: 1
;;;					cel: 0
;;;					illegalBits: 0
;;;					ignoreActors:
;;;					setPri:
;;;					setStep: 1 1
;;;					setCycle: Forward
;;;					setMotion: MoveTo 232 91 self
;;;				)
;;;				(= cycles 11)
;;;			)
;;;			(28
;;;				(Print 530 75 #dispose #at -1 10)
;;;				(= cycles 11)
;;;			)
;;;			(29
;;;				(Print 530 76 #dispose #at -1 10)
;;;			)
;;;			(30
;;;				(Print 530 77 #dispose #at -1 10)
;;;				(ego setPri: 2 setMotion: MoveTo 49 91 self)
;;;				(= cycles 22)
;;;			)
;;;			(31
;;;				(Print 530 78 #dispose #at -1 10)
;;;				(= cycles 22)
;;;			)
;;;			(32
;;;				(Print 530 79 #dispose #at -1 144)
;;;			)
;;;			(33
;;;				(music fade:)
;;;				(Print 530 80 #dispose #at -1 10)
;;;				(= seconds 3)
;;;			)
;;;			(34
;;;				(= seconds (= cycles 0))
;;;				(Print
;;;					(Format @msgBuf 530 81 expletive)
;;;					#dispose
;;;					#at -1 10
;;;				)
;;;				(music number: 4 loop: 1 play:)
;;;				(ego setLoop: 2 cel: 0 cycleSpeed: 0 setCycle: EndLoop self)
;;;				(= state 65)
;;;			)
;;;			(35
;;;				(Ok)
;;;				(HandsOff)
;;;				(Print 530 82)
;;;				(if (>= filthLevel 3)
;;;					(Print 530 83 #at -1 144)
;;;				)
;;;				(= currentStatus 533)
;;;				(ego
;;;					view: 532
;;;					setLoop: 0
;;;					illegalBits: 0
;;;					posn: 129 116
;;;					setPri: 11
;;;					cycleSpeed: 1
;;;					moveSpeed: 1
;;;					setMotion: MoveTo 128 47 self
;;;				)
;;;			)
;;;			(36
;;;				(= currentStatus 536)
;;;				(User canInput: TRUE)
;;;			)
;;;			(37
;;;				(Ok)
;;;				(HandsOff)
;;;				(ego setLoop: 1 cel: 0 setCycle: EndLoop self)
;;;			)
;;;			(38
;;;				(ego setLoop: 0 cel: 0 setCycle: EndLoop self)
;;;			)
;;;			(39
;;;				(ego setLoop: 2 cel: 0 setCycle: EndLoop self)
;;;			)
;;;			(40
;;;				(ego get: 19 setLoop: 0 cel: 0 setCycle: EndLoop self)
;;;			)
;;;			(41
;;;				(theGame changeScore: 25)
;;;				(Print 530 84)
;;;				(if (>= filthLevel 3)
;;;					(Print 530 85 #at -1 144)
;;;				)
;;;				(User canInput: TRUE)
;;;			)
;;;			(42
;;;				(Ok)
;;;				(HandsOff)
;;;				(ego
;;;					cycleSpeed: 1
;;;					setLoop: 0
;;;					setCycle: Walk
;;;					setMotion: MoveTo 129 116 self
;;;				)
;;;			)
;;;			(43
;;;				(ego posn: 128 132)
;;;				(NormalEgo loopN)
;;;				(= currentStatus 0)
;;;			)
;;;			(44
;;;				(Ok)
;;;				(HandsOff)
;;;				((Inventory at: iMarijuana) view: 27)
;;;				(Format ((Inventory at: iMarijuana) name?) 530 86)
;;;				(theGame changeScore: 100)
;;;				(Print 530 87 #at -1 10 #icon 20 0 0)
;;;				(= seconds 3)
;;;			)
;;;			(45
;;;				(Print 530 88)
;;;				(= ropeState 2)
;;;				(NormalEgo)
;;;			)
;;;			(46
;;;				(HandsOff)
;;;				(Ok)
;;;				(= currentStatus 531)
;;;				(ego
;;;					illegalBits: 0
;;;					ignoreActors:
;;;					setMotion: MoveTo 141 129 self
;;;				)
;;;			)
;;;			(47
;;;				(music stop:)
;;;				(ego
;;;					view: 534
;;;					posn: 142 128
;;;					setLoop: 1
;;;					cel: 0
;;;					setCycle: Forward
;;;				)
;;;				(= cycles (* 3 (- (NumCels ego) 1)))
;;;			)
;;;			(48
;;;				(ego setLoop: 0 cel: 0 setCycle: EndLoop self)
;;;			)
;;;			(49
;;;				(if (> 2 (++ local0))
;;;					(= state 46)
;;;				)
;;;				(= seconds 3)
;;;			)
;;;			(50
;;;				(Print 530 89 #at -1 10)
;;;				(ego setLoop: 1 setCycle: Forward)
;;;				(= cycles (* 3 (- (NumCels ego) 1)))
;;;			)
;;;			(51
;;;				(ego setLoop: 2 cel: 0 setCycle: EndLoop self)
;;;			)
;;;			(52
;;;				(theGame changeScore: 20)
;;;				(Print 530 90
;;;					#at 10 5
;;;					#width 290
;;;				)
;;;				(= ropeState 3)
;;;				(soundFX stop:)
;;;				(music play:)
;;;				(HandsOn)
;;;			)
;;;			(53
;;;				(= ropeState 4)
;;;				(theGame changeScore: 20)
;;;				(Ok)
;;;				(HandsOff)
;;;				(ego
;;;					view: 534
;;;					posn: 141 129
;;;					setPri: 9
;;;					setLoop: 3
;;;					setCycle: Forward
;;;				)
;;;				(aRope
;;;					view: 530
;;;					setLoop: 0
;;;					cel: 0
;;;					posn: 227 72
;;;					cycleSpeed: 1
;;;					setCycle: EndLoop self
;;;				)
;;;			)
;;;			(54
;;;				(aRope stopUpd:)
;;;				(= cycles 10)
;;;			)
;;;			(55
;;;				(Print 530 91 #at 10 5 #width 290)
;;;				(NormalEgo 1)
;;;				(ego put: iMarijuana -1)
;;;				(= currentStatus egoNORMAL)
;;;			)
;;;			(56
;;;				(HandsOff)
;;;				(Ok)
;;;				(ego view: 534 setLoop: 4 cel: 0 setCycle: EndLoop self)
;;;			)
;;;			(57
;;;				(theGame changeScore: 50)
;;;				(Print 530 92 #at 10 5 #width 290)
;;;				(= currentEgoView 803)
;;;				((Inventory at: iDress) view: 31)
;;;				(NormalEgo 2)
;;;			)
;;;			(58
;;;				(HandsOff)
;;;				(ego posn: 141 129)
;;;				(NormalEgo 0)
;;;				(aRope
;;;					view: 530
;;;					posn: 227 72
;;;					cycleSpeed: 1
;;;					setLoop: 1
;;;					cel: 0
;;;					setCycle: EndLoop self
;;;				)
;;;			)
;;;			(59
;;;				(theGame setScript: (ScriptID DYING))
;;;				((ScriptID DYING)
;;;					caller: 27
;;;					register: (Format @msgBuf 530 93)
;;;					next: (Format @titleBuf 530 94)
;;;				)
;;;			)
;;;			(60
;;;				(Ok)
;;;				(HandsOff)
;;;				(= currentStatus 532)
;;;				(ego
;;;					illegalBits: 0
;;;					ignoreActors:
;;;					setMotion: MoveTo 141 129 self
;;;				)
;;;			)
;;;			(61
;;;				(if (!= currentEgoView 803)
;;;					(self cue:)
;;;				else
;;;					(Print 530 95 #at -1 10)
;;;					(ego
;;;						view: 534
;;;						setLoop: 5
;;;						cel: 0
;;;						cycleSpeed: 1
;;;						setCycle: EndLoop self
;;;					)
;;;				)
;;;			)
;;;			(62
;;;				(ego
;;;					view: 534
;;;					setLoop: 6
;;;					cel: 0
;;;					posn: 149 107
;;;					cycleSpeed: 1
;;;					setCycle: EndLoop self
;;;				)
;;;			)
;;;			(63
;;;				(Print 530 96)
;;;				(music fade:)
;;;				(= seconds 3)
;;;			)
;;;			(64
;;;				(curRoom newRoom: 535)
;;;			)
;;;			(65
;;;				(HandsOff)
;;;				(Print
;;;					(Format @msgBuf 530 81 expletive)
;;;					#at -1 10
;;;					#dispose
;;;				)
;;;				(music number: 4 loop: 1 play:)
;;;				(ego
;;;					view: 813
;;;					setLoop: (if onOtherSide 1 else 0)
;;;					illegalBits: 0
;;;					ignoreActors:
;;;					setPri: 5
;;;					setStep: 1 8
;;;					cel: 0
;;;					cycleSpeed: 0
;;;					setCycle: EndLoop self
;;;				)
;;;			)
;;;			(66
;;;				(ego setMotion: theJump)
;;;				(theJump y: 300)
;;;			)
;;;			(67
;;;				(cls)
;;;				(if (or (== currentStatus 534) (== currentStatus 535))
;;;					(Print 530 97)
;;;				else
;;;					(Print 530 98)
;;;				)
;;;				(if debugging
;;;					(NormalEgo)
;;;					(= currentStatus egoNORMAL)
;;;					(if onOtherSide
;;;						(ego posn: 210 77 setStep: 2 1)
;;;					else
;;;						(ego posn: 159 158)
;;;					)
;;;				else
;;;					(theGame setScript: (ScriptID DYING))
;;;					((ScriptID DYING)
;;;						caller: 814
;;;						register: (Format @msgBuf 530 99)
;;;					)
;;;				)
;;;			)
;;;		)
;;;	)
;;;	
;;;	(method (handleEvent event)
;;;		(if (event claimed?)
;;;		;(if (or (!= (event type?) saidEvent) (event claimed?))
;;;			(return)
;;;		)
;;;		(cond 
;;;			((Said 'make,weave/blade,hemp')
;;;				(cond 
;;;					((!= currentStatus egoNORMAL)
;;;						(GoodIdea)
;;;					)
;;;					(onOtherSide
;;;						(Print 530 2)
;;;					)
;;;					(else
;;;						(switch ropeState
;;;							(0 (Print 530 3))
;;;							(1 (RmPalmeras changeState: 44))
;;;							(else  (Print 530 4))
;;;						)
;;;					)
;;;				)
;;;			)
;;;			((and (>= ropeState 4) (Said 'unfasten'))
;;;				(Print 530 5)
;;;			)
;;;			((or (Said 'hemp/boulder') (Said 'throw/blade,hemp'))
;;;				(if (!= currentStatus egoNORMAL)
;;;					(GoodIdea)
;;;				else
;;;					(switch ropeState
;;;						(0
;;;							(Print 530 6)
;;;						)
;;;						(1
;;;							(Print 530 7)
;;;							(ego put: iMarijuana curRoomNum)
;;;							(= ropeState 0)
;;;						)
;;;						(2
;;;							(if (not (& (ego onControl:) cGREEN))
;;;								(Print 530 8)
;;;							else
;;;								(RmPalmeras changeState: 46)
;;;							)
;;;						)
;;;						(3
;;;							(ItIs)
;;;						)
;;;						(4
;;;							(ItIs)
;;;						)
;;;						(531
;;;							(Print 530 9)
;;;						)
;;;					)
;;;				)
;;;			)
;;;			((Said 'attach/hemp>')
;;;				(cond 
;;;					((Said '//coconut')
;;;						(Print 530 10)
;;;					)
;;;					((!= currentStatus 531)
;;;						(event claimed: TRUE)
;;;						(Print 530 11)
;;;					)
;;;					((Said '/[/noword]')
;;;						(Print 530 12)
;;;					)
;;;					((Said '//palm')
;;;						(switch ropeState
;;;							(2
;;;								(Print 530 11)
;;;							)
;;;							(3
;;;								(RmPalmeras changeState: 53)
;;;							)
;;;							(4
;;;								(ItIs)
;;;							)
;;;							(531
;;;								(Print 530 13)
;;;							)
;;;							(else
;;;								(Print 530 14)
;;;							)
;;;						)
;;;					)
;;;				)
;;;			)
;;;			(
;;;				(or
;;;					(Said 'make/belt,throw,belt,barstool,belt')
;;;					(Said 'attach/hemp/i,self,entertainer')
;;;					(Said 'use/dress')
;;;					(Said 'break/skirt,cloth,dress')
;;;				)
;;;				(cond 
;;;					((!= currentStatus egoNORMAL)
;;;						(GoodIdea)
;;;					)
;;;					((== currentEgoView 803)
;;;						(Print 530 15)
;;;					)
;;;					((< ropeState 3)
;;;						(Print 530 16)
;;;					)
;;;					((< ropeState 4)
;;;						(Print 530 17)
;;;					)
;;;					((> ropeState 4)
;;;						(Print 530 18)
;;;					)
;;;					(else
;;;						(RmPalmeras changeState: 56)
;;;					)
;;;				)
;;;			)
;;;			(
;;;				(or
;;;					(Said 'cross/canyon,hemp')
;;;					(Said 'cross//canyon,hemp')
;;;					(Said 'bang,go,grab,exit,use,climb/hemp,belt')
;;;					(Said 'bang,go,grab,exit,use,climb//hemp,belt')
;;;				)
;;;				(cond 
;;;					((!= currentStatus egoNORMAL)
;;;						(Print 530 19)
;;;					)
;;;					(onOtherSide
;;;						(Print 530 20)
;;;					)
;;;					((< ropeState 4)
;;;						(Print 530 21)
;;;					)
;;;					((> 129 (ego y?))
;;;						(NotClose)
;;;					)
;;;					(else
;;;						(RmPalmeras changeState: 60)
;;;					)
;;;				)
;;;			)
;;;			((and onOtherSide (Said '/hemp'))
;;;				(Print 530 22)
;;;			)
;;;			((and ropeState (Said 'test,look/hemp,knot'))
;;;				(Print 530 23)
;;;			)
;;;			((and (== ropeState 3) (Said 'use,climb,throw/hemp'))
;;;				(Print 530 24)
;;;			)
;;;			((Said 'climb/palm')
;;;				(cond 
;;;					((== currentStatus 536)
;;;						(RmPalmeras changeState: 42)
;;;					)
;;;					((!= currentStatus egoNORMAL)
;;;						(GoodIdea)
;;;					)
;;;					(onOtherSide
;;;						(Print 530 25)
;;;					)
;;;					((& (ego onControl:) cMAGENTA)
;;;						(Print 530 26)
;;;					)
;;;					((not (& (ego onControl:) cRED))
;;;						(Print 530 27)
;;;					)
;;;					(else
;;;						(RmPalmeras changeState: 35)
;;;					)
;;;				)
;;;			)
;;;			((and (== currentStatus 536) (Said 'go,climb<down'))
;;;				(RmPalmeras changeState: 42)
;;;			)
;;;			((Said 'pick,get/coconut')
;;;				(cond 
;;;					((ego has: iCoconuts)
;;;						(Print 530 28)
;;;					)
;;;					((!= currentStatus 536)
;;;						(Print 530 29)
;;;					)
;;;					(else
;;;						(RmPalmeras changeState: 37)
;;;					)
;;;				)
;;;			)
;;;			((Said 'climb[<down]/boulder,canyon,ledge')
;;;				(Print 530 30)
;;;				(Print 530 31 #at -1 144)
;;;			)
;;;			((Said 'climb<up[/boulder,canyon,ledge]')
;;;				(Print 530 32)
;;;			)
;;;			((or (Said 'look<down') (Said 'look/cliff,edge,canyon'))
;;;				(Print 530 33)
;;;			)
;;;			(
;;;				(and
;;;					(not onOtherSide)
;;;					(or
;;;						(Said 'look/air,hose')
;;;						(Said 'climb/cliff,cliff,cliff')
;;;						(Said 'look<up')
;;;					)
;;;				)
;;;				(Print 530 34)
;;;				(Print 530 35)
;;;			)
;;;			((Said 'pick,get/blade,bush,bush,hemp')
;;;				(if (!= currentStatus egoNORMAL)
;;;					(GoodIdea)
;;;				else
;;;					(switch ropeState
;;;						(0
;;;							(if (not (& (ego onControl:) cCYAN))
;;;								(NotClose)
;;;							else
;;;								(RmPalmeras changeState: 7)
;;;							)
;;;						)
;;;						(1
;;;							(Print 530 36)
;;;						)
;;;						(2
;;;							(Print 530 37)
;;;						)
;;;						(else
;;;							(Print 530 38)
;;;						)
;;;					)
;;;				)
;;;			)
;;;			(
;;;				(or
;;;					(Said 'make,drag/blade')
;;;					(Said 'burn,smoke/bush,bush,blade')
;;;				)
;;;				(cond 
;;;					((!= currentStatus egoNORMAL)
;;;						(GoodIdea)
;;;					)
;;;					((not (ego has: iMarijuana))
;;;						(Print 530 39)
;;;					)
;;;					((> ropeState 1)
;;;						(Print 530 40)
;;;					)
;;;					(else
;;;						(= currentStatus 534)
;;;						(RmPalmeras changeState: 15)
;;;					)
;;;				)
;;;			)
;;;			(
;;;				(or
;;;					(Said 'backdrop/blade,bush/lip')
;;;					(Said 'eat,eat/bush,bush,blade')
;;;				)
;;;				(cond 
;;;					((!= currentStatus egoNORMAL)
;;;						(GoodIdea)
;;;					)
;;;					((not (ego has: iMarijuana))
;;;						(Print 530 41)
;;;					)
;;;					((> ropeState 1)
;;;						(Print 530 42)
;;;					)
;;;					(else
;;;						(= currentStatus 535)
;;;						(RmPalmeras changeState: 15)
;;;					)
;;;				)
;;;			)
;;;			((Said 'unfasten')
;;;				(Print 530 43)
;;;			)
;;;			((Said 'drag,drag,drag/palm')
;;;				(Print 530 44)
;;;			)
;;;			(
;;;				(and
;;;					(ego has: iCoconuts)
;;;					(or
;;;						(Said 'use,attach/coconut')
;;;						(Said 'use,attach//coconut')
;;;					)
;;;				)
;;;				(Print 530 45)
;;;			)
;;;			((Said 'look>')
;;;				(cond 
;;;					((Said '/boulder')
;;;						(cond 
;;;							((== ropeState 531)
;;;								(Print 530 22)
;;;							)
;;;							((== ropeState 4)
;;;								(Print 530 46)
;;;								(Print 530 47 #at -1 144)
;;;							)
;;;							(else
;;;								(Print 530 48)
;;;							)
;;;						)
;;;					)
;;;					((Said '/carpet') (Print 530 49))
;;;					((Said '/palm')
;;;						(cond 
;;;							(onOtherSide
;;;								(Print 530 50)
;;;							)
;;;							((== currentStatus 536)
;;;								(Print 530 51)
;;;							)
;;;							(else
;;;								(Print 530 52)
;;;							)
;;;						)
;;;					)
;;;					((Said '/coconut')
;;;						(if (>= filthLevel 2)
;;;							(Print 530 53 #at -1 144)
;;;						)
;;;						(cond 
;;;							((ego has: iCoconuts)
;;;								(event claimed: FALSE)
;;;							)
;;;							(onOtherSide
;;;								(Print 530 54)
;;;							)
;;;							(else
;;;								(Print 530 55)
;;;								(Print 530 56)
;;;							)
;;;						)
;;;					)
;;;					(
;;;						(and
;;;							(not (ego has: iMarijuana))
;;;							(or (Said '/blade') (Said '/bush,exit'))
;;;						)
;;;						(if (== currentStatus 536)
;;;							(Print 530 57)
;;;						else
;;;							(Print 530 58)
;;;						)
;;;					)
;;;					((Said '/bush,exit')
;;;						(cond 
;;;							((== currentStatus 536)
;;;								(Print 530 57)
;;;							)
;;;							(onOtherSide
;;;								(Print 530 59)
;;;							)
;;;							(else
;;;								(Print 530 60)
;;;							)
;;;						)
;;;					)
;;;					((Said '[/ledge,area]')
;;;						(cond 
;;;							((== currentStatus 536)
;;;								(Print 530 61)
;;;							)
;;;							(onOtherSide
;;;								(Print 530 62)
;;;							)
;;;							(else
;;;								(Print 530 63)
;;;							)
;;;						)
;;;					)
;;;				)
;;;			)
;;;		)
;;;	)
;;;)
		
(instance aRope of Prop
	(properties
		y 1111
		x 999
		view 530
		loop 1
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: setPri: 7)
	)
)

(instance RopeScript of Script
	(method (cue)
		(aRope stopUpd:)
		(Print 530 100 #at 10 5 #width 290)
	)
)

(instance atpTits of PicView
	(properties
		y 157
		x 169
		view 532
		loop 3
		priority 7
		signal ignrAct
	)
)

;;;(instance theJump of Jump
;;;	(method (init)
;;;		(super init: ego RmPalmeras)
;;;		(self yStep: 2)
;;;	)
;;;)
