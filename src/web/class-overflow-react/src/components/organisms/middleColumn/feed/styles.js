import styled from 'styled-components'

export const Container = styled.div`
  width: 50%;
  height: 100vh;
  display: flex;
  justify-content: center;
  flex-direction: column;
  border-left: 1px solid hsl(210deg 4% 26%);
`
export const LeftColumn = styled.div`
  width: 150px;
  height: auto;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: var(--theme-dark-text);
  font-size: 1rem;
`
export const RightColumn = styled.div`
  width: 100%;
  height: auto;
  color: hsl(206deg 100% 60%);
  h3 {
    color: hsl(210deg 11% 89%);
  }
  .date {
    font-weight: bold;
    color: hsl(210deg 11% 89%);
    font-size: 1rem;
  }
`

export const Badges = styled.div`
  display: flex;
  max-width: 300px;
  height: auto;
  justify-content: space-between;
`

export const Badge = styled.div`
  padding: 14px;
  height: 40px;
  background-color: hsl(205deg 14% 28%);
  border-radius: 4px;
  display: flex;
  text-align: center;
  justify-content: center;
  align-items: center;
  color: hsl(205deg 47% 74%);
  font-size: 0.9rem;
`
export const Content = styled.div`
  height: 740px;
  width: 100%;
`

export const FeedPost = styled.div`
  display: flex;
  width: 100%;
  height: 240px;
  padding: 20px;
  border: 1px solid hsl(210deg 4% 26%);
`

export const Title = styled.p`
  font-size: 1.5rem;
  word-wrap: break-word;
`
export const PostContent = styled.div`
  display: flex;
  flex-direction: row;
  width: 100%;
  height: auto;
  justify-content: space-between;
  align-items: center;
  padding: 16px 32px;
  background-color: hsl(0deg 0% 22%);
  color: white;
`

export const TagContent = styled.div`
  width: 100%;
  font-size: 16px;
  color: black;
  top: 350px;
  --rti-bg: #fff;
  --rti-main: #3182ce;
  --rti-radius: 0.375rem;
  --rti-s: 0.5rem;
  --rti-tag: #edf2f7;
  --rti-tag-remove: #e53e3e;
  align-items: center;
  bg: var(--rti-bg);
  border-radius: var(--rti-radius);
  gap: var(--rti-s);
  line-height: 1.4;
  margin-top: 24px;
  div {
    padding: 0;
  }
  input {
    width: 100%;
    background-color: #f0f0f0;
  }
`
