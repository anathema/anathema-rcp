<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" encoding="ISO-8859-1" indent="yes"/>
	<xsl:template match="/">
		<charmTraitsList>
     		<xsl:apply-templates select="@* | node() " />
	    </charmTraitsList>
	</xsl:template>
	
	<xsl:template match="charm[prerequisite/trait/@value != 1 or prerequisite/essence/@value != 1]">
        <xsl:variable name="primaryMinimum" select="prerequisite/trait/@value"/>
        <xsl:variable name="essenceMinimum" select="prerequisite/essence/@value"/>
	      <charmTraits>
			<xsl:attribute name="charmId"><xsl:value-of select="@id"/></xsl:attribute>
			<xsl:if test="$essenceMinimum != 1">
				<xsl:attribute name="essenceMinimum"><xsl:value-of select="$essenceMinimum"/></xsl:attribute>
			</xsl:if>
			<xsl:if test="$primaryMinimum != 1">
			  <xsl:attribute name="primaryMinimum"><xsl:value-of select="$primaryMinimum"/></xsl:attribute>
			</xsl:if>
	      </charmTraits>
	</xsl:template>
</xsl:stylesheet>