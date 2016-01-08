#version 330 core

layout (location = 0) out vec4 color;


in DATA
{
	vec2 tc;
} fs_in;

uniform sampler2D tex;
uniform vec4 inColor;
uniform vec4 seed;

void main()
{
	color = texture(tex, fs_in.tc)*inColor*seed;
	if (color.w < 1.0)
		discard;
}