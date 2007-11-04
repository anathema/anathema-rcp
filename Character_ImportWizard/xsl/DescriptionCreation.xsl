<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" encoding="ISO-8859-1" indent="yes"/>
	
	<xsl:param name="bundleVersion" />
	
	<xsl:template match="/">
		<model bundleVersion="{$bundleVersion}">
			<xsl:apply-templates select="ExaltedCharacter/Description/*"/>
		</model>
	</xsl:template>
	
	<xsl:template match="ExaltedCharacter/Description/*">
		<xsl:variable name="content"></xsl:variable>
		<xsl:copy>
			<xsl:text><![CDATA[</xsl:text>
				<xsl:value-of select="." />
			<xsl:text>]]></xsl:text>
		</xsl:copy>
	</xsl:template>
	
	<xsl:template match="Periphrase">
		<Periphrasis>
			<xsl:value-of select="." />
		</Periphrasis>
	</xsl:template>
</xsl:stylesheet>