/* mobiles.rex

   usage:
      rexx mobiles.rex
*/

/* test cases */
mobile = buildMobile(bks)
say "Mobile:" mobile
say "totalWeight =" totalWeight(1)
say "totalHeight =" totalHeight(1)
say "isBalanced?" isBalanced(1)
say
mobile = buildMobile(cord)
say "Mobile:" mobile
say "totalWeight =" totalWeight(1)
say "totalHeight =" totalHeight(1)
say "isBalanced?" isBalanced(1)
say
mobile = buildMobile(busy)
say "Mobile:" mobile
say "totalWeight =" totalWeight(1)
say "totalHeight =" totalHeight(1)
say "isBalanced?" isBalanced(1)
say
mobile = buildMobile(broke)
say "Mobile:" mobile
say "totalWeight =" totalWeight(1)
say "totalHeight =" totalHeight(1)
say "isBalanced?" isBalanced(1)
say

exit 0 /*don't drop into the internal procedures! */

/*
totalWeight -- 
  Compute the total weight of a mobile by traversing its
  structure and summing the weights of its sub-mobiles. 
  Assume that the weight of struts and cords is 0.
  
  Parameters:
    arg(1): the node number to begin traversing from
  Returns:
    The total weight of the mobile.
*/
totalWeight : procedure expose left. rght. wght.
	node = arg(1)
	if (left.node=0 & rght.node=0) then
		return wght.node
	else do
		leftNode = 2 * node
		rghtNode = leftNode + 1
		return totalWeight(leftNode) + totalWeight(rghtNode)
	end

/*
totalHeight -- 
  Compute the total height of the mobile by traversing its
  structure and finding the greatest height of its sub-mobiles.
  
  Parameters:
    arg(1): node number to begin traversing from
  Returns:
    the greatest height of all sub-mobiles
*/
totalHeight : procedure expose left. rght. hght.
	node = arg(1)
	if (left.node=0 & rght.node=0) then
		return hght.node
	else do
		leftNode = 2 * node
		rghtNode = leftNode + 1
		leftHeight = totalHeight(leftNode)
		rghtHeight = totalHeight(rghtNode)
		return hght.node + max(leftHeight, rghtHeight)
	end

/*
isBalanced -- 
  Determines whether a mobile is balanced.
  A balanced mobile is defined as follows:
  - A mobile with no struts is always balanced.
  - A mobile with a strut is balanced if
    -- the product of the weight of the mobile on the left with the 
       length of the left section of the strut is the same as the 
       product of the weight of the mobile on the right with the 
       length of the right section of the strut
    -- the left mobile is balanced
    -- the right mobile is balanced
    
    Parameters:
      arg(1): the node number to begin traversing from
    Return:
      0 if the mobile is balanced
      1 if the mobile is not balanced
*/
isBalanced : procedure expose left. rght. wght.
	node = arg(1)
	if (left.node=0 & rght.node=0) then
		return 1
	else do
		leftNode = 2 * node
		rghtNode = leftNode + 1
		leftVal = left.node * totalWeight(leftNode)
		rghtVal = rght.node * totalWeight(rghtNode)
		if (leftVal=rghtVal & isBalanced(leftNode) & isBalanced(rghtNode)) then
			return 1
		else
			return 0
		end

/*
buildMobile --
  Builds the mobile based on pre-defined values.
  Uses a switch statement to determine which mobile to build.
  
  Parameters:
    arg(1): name of mobile to build
  
  Returns:
    the name of the built mobile
*/
buildMobile : procedure expose left. rght. hght. wght.
	mobile = arg(1)
	left.=0		/* distance to left node */
	rght.=0		/* distance to right node */
	hght.=0		/* height of cord */
	wght.=0		/* weight attached */
	
	select
		when (mobile = bks) then do
			hght.1 = 2
			left.1 = 20
			rght.1 = 10
			hght.2 = 1
			left.2 = 5
			rght.2 = 5
			hght.3 = 5
			wght.3 = 20
			hght.4 = 1
			wght.4 = 5
			hght.5 = 1
			left.5 = 4
			rght.5 = 6
			hght.10 = 5
			wght.10 = 3
			hght.11 = 1
			wght.11 = 2
		end
		when (mobile = cord) then do
			hght.1 = 4
			wght.1 = 5
		end
		when (mobile = busy) then do
			hght.1 = 2
			left.1 = 9
			rght.1 = 18
			hght.2 = 1
			left.2 = 8
			rght.2 = 8
			hght.3 = 7
			left.3 = 12
			rght.3 = 5
			hght.4 = 2
			wght.4 = 17
			hght.5 = 1
			left.5 = 12
			rght.5 = 5
			hght.6 = 1
			wght.6 = 5
			hght.7 = 1
			left.7 = 2
			rght.7 = 1
			hght.10 = 1
			left.10 = 8
			rght.10 = 2
			hght.11 = 1
			wght.11 = 12
			hght.14 = 1
			wght.14 = 4
			hght.15 = 6
			wght.15 = 8
			hght.20 = 5
			wght.20 = 1
			hght.21 = 1
			left.21 = 6
			rght.21 = 2
			hght.42 = 1
			wght.42 = 1
			hght.43 = 5
			left.43 = 4
			rght.43 = 8
			hght.86 = 3
			wght.86 = 2
			hght.87 = 1
			wght.87 = 1
		end
		when (mobile = broke) then do
			hght.1 = 1
			left.1 = 12
			rght.1 = 4
			hght.2 = 1
			wght.2 = 4
			hght.3 = 3
			wght.3 = 2
		end
	end
	return mobile